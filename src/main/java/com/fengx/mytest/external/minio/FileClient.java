package com.fengx.mytest.external.minio;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class FileClient extends MinioClient {

    public final String FILE_SEPARATOR = "/";

    public final String DEFAULT_BUCKET = "default";

    public final String TEMP_BUCKET = "temp-bucket";

    public FileClient(String api, String accesskey, String secretkey) {
        this(MinioClient.builder()
                .endpoint(api)
                .credentials(accesskey, secretkey)
                .build());
    }

    public Boolean bucketExists(String bucket) {
        try {
            return this.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void makeAbsentBucket(String bucket) {
        try {
            if (!this.bucketExists(bucket)) {
                this.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException |
                InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
    }

    public List<String> listFileNames(String modelName) {
        return listFileNames(DEFAULT_BUCKET, modelName);
    }

    public List<String> listFileNames(String bucket, String modelName) {
        List<String> objectNames = new ArrayList<>(0);

        try {
            if (!bucket.equals(DEFAULT_BUCKET) && !this.bucketExists(bucket)) {
                return objectNames;
            }

            Iterable<Result<Item>> results = this.listObjects(
                    ListObjectsArgs.builder().bucket(bucket).prefix(modelName.concat(FILE_SEPARATOR)).build());

            for (Result<Item> item : results) {
                objectNames.add(item.get().objectName());
            }
        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException
                | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
        return objectNames;
    }

    public StatObjectResponse getFileInfo(String filename) {
        try {
            return this.statObject(
                    StatObjectArgs.builder().bucket(DEFAULT_BUCKET).object(filename).build());
        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ObjectWriteResponse uploadFile(MultipartFile file, String modelName) {
        return uploadFile(file, DEFAULT_BUCKET, modelName.concat(FILE_SEPARATOR).concat(UUID.randomUUID().toString()));
    }

    public ObjectWriteResponse uploadFile(MultipartFile file, String bucket, String filename) {
        try {
            return this.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(filename)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ObjectWriteResponse mergeFile(List<String> fromFilenames, String toModelName) {
        return mergeFile(TEMP_BUCKET, fromFilenames, DEFAULT_BUCKET, toModelName.concat(FILE_SEPARATOR).concat(UUID.randomUUID().toString()));
    }

    public ObjectWriteResponse mergeFile(String fromBucket, List<String> fromFilenames, String toBucket, String toFilename) {
        try {
            List<ComposeSource> sourceObjectList = fromFilenames.stream()
                    .map(item -> ComposeSource.builder()
                            .bucket(fromBucket)
                            .object(item)
                            .build())
                    .collect(Collectors.toList());
            ObjectWriteResponse response = this.composeObject(
                    ComposeObjectArgs.builder()
                            .bucket(toBucket)
                            .object(toFilename)
                            .sources(sourceObjectList)
                            .build());

            List<DeleteObject> delObjects = fromFilenames.stream()
                    .map(DeleteObject::new)
                    .collect(Collectors.toList());
            Iterable<Result<DeleteError>> results =
                    this.removeObjects(
                            RemoveObjectsArgs.builder().bucket(fromBucket).objects(delObjects).build());
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                log.error("Error in deleting object " + error.objectName() + "; " + error.message());
            }
            return response;
        } catch (IOException | XmlParserException | ServerException | InsufficientDataException | NoSuchAlgorithmException |
                InvalidKeyException | InvalidResponseException | ErrorResponseException | InternalException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public InputStream getInputStream(String filename, Long offset, Long contentLength) throws IOException, InvalidResponseException, InvalidKeyException,
            NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        return getInputStream(DEFAULT_BUCKET, filename, offset, contentLength);
    }

    public InputStream getInputStream(String filename) throws IOException, InvalidResponseException, InvalidKeyException,
            NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        return getInputStream(DEFAULT_BUCKET, filename, null, null);
    }

    public InputStream getInputStream(String bucket, String filename, Long offset, Long contentLength) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

        if (offset != null && contentLength != null) {
            return this.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(filename)
                    .offset(offset)
                    .length(contentLength)
                    .build());
        }

        return this.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(filename)
                .build());
    }

    @PostConstruct
    private void init() {
        try {
            this.makeAbsentBucket(TEMP_BUCKET);
            this.makeAbsentBucket(DEFAULT_BUCKET);

            String sb = "{\"Version\":\"2012-10-17\"," +
                    "\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":" +
                    "{\"AWS\":[\"*\"]},\"Action\":[\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"," +
                    "\"s3:GetBucketLocation\"],\"Resource\":[\"arn:aws:s3:::" + DEFAULT_BUCKET +
                    "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:PutObject\",\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\"],\"Resource\":[\"arn:aws:s3:::" +
                    DEFAULT_BUCKET +
                    "/*\"]}]}";
            this.setBucketPolicy(
                    SetBucketPolicyArgs.builder()
                            .bucket(DEFAULT_BUCKET)
                            .config(sb)
                            .build());
        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
    }

    private FileClient(MinioClient client) {
        super(client);
    }
}
