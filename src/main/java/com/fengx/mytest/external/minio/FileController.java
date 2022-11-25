package com.fengx.mytest.external.minio;

import com.alibaba.fastjson.JSONObject;
import com.fengx.mytest.springboot.response.FailedResponse;
import com.fengx.mytest.springboot.response.ObjectResponse;
import com.fengx.mytest.springboot.response.Response;
import com.google.common.collect.Sets;
import io.minio.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * oss 对象存储服务
 * <p>
 * store 存储器
 */
@Slf4j
@RestController
@RequestMapping("/minio")
public class FileController {

    private static MinioClient minioClient;

    private static final String TEMP_DIR = "temp-dir";

    private static final String FILE_SEPARATOR = "/";

    @PostConstruct
    public void init() {
        minioClient = MinioClient.builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials("admin", "123456789")
                .build();
    }

    @PostMapping(value = "/upload")
    public Response upload(@RequestParam(name = "file") MultipartFile file, @RequestParam(defaultValue = "default") String moduleName) {
        if (file.isEmpty()) {
            return new FailedResponse<>("文件不能为空！");
        }
        try {
            // 创建桶
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(moduleName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(moduleName).build());
            }

            // Upload known sized input stream.
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(
                    PutObjectArgs.builder().bucket(moduleName).object(UUID.randomUUID().toString()).stream(
                            file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            return new ObjectResponse<>(objectWriteResponse.etag() + " " + objectWriteResponse.versionId() + " " +
                    objectWriteResponse.bucket() + " " + objectWriteResponse.object() + " " + objectWriteResponse.region());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new FailedResponse<>();
    }

    /**
     * 按分片顺序上传
     * @return 0~n 叫前端上传对应分片序号
     *         -1 上传完成
     *         -2 文件md5对不上
     *
     */
    @PostMapping("/big/upload")
    public String uploadBigFile(@RequestParam("file") MultipartFile file,
                                @RequestParam(defaultValue = "default") String moduleName,
                                HttpServletRequest request) {
        try {
            String md5 = request.getParameter("md5");
            int totalPieces = Integer.parseInt(request.getParameter("totalPieces"));
            String fileName = request.getParameter("fileName");
            log.info("上传文件的md5:" + md5);
            // 查询数据库如果存在则是文件秒传
            log.info("文件名:" + fileName + " moduleName:" + moduleName);
            // else ->
            // 上传合并
            int index = uploadBigFileCore(file,
                    Integer.parseInt(request.getParameter("sliceIndex")),
                    totalPieces,
                    md5);
            if (index == -1) {
                // 完成上传从缓存目录合并迁移到正式目录
                List<ComposeSource> sourceObjectList = Stream.iterate(0, i -> ++i)
                        .limit(totalPieces)
                        .map(i -> ComposeSource.builder()
                                .bucket(TEMP_DIR)
                                .object(md5.concat(FILE_SEPARATOR).concat(Integer.toString(i)))
                                .build())
                        .collect(Collectors.toList());
                ObjectWriteResponse response = minioClient.composeObject(
                        ComposeObjectArgs.builder()
                                .bucket("default")
                                .object(moduleName.concat(FILE_SEPARATOR).concat(fileName))
                                .sources(sourceObjectList)
                                .build());
                // 验证md5
                try (InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                      .bucket(response.bucket())
                      .object(response.object())
                      .build())) {
                    String md5Hex = DigestUtils.md5Hex(stream);
                    if (!md5Hex.equals(md5)) {
                        return "-2";
                    }
                }
                // 删除所有的分片文件，只保留合并后的文件(可以异步)
                List<DeleteObject> delObjects = Stream.iterate(0, i -> ++i)
                        .limit(totalPieces)
                        .map(i -> new DeleteObject(md5.concat(FILE_SEPARATOR).concat(Integer.toString(i))))
                        .collect(Collectors.toList());
                Iterable<Result<DeleteError>> results =
                        minioClient.removeObjects(
                                RemoveObjectsArgs.builder().bucket(TEMP_DIR).objects(delObjects).build());
                for (Result<DeleteError> result : results) {
                    DeleteError error = result.get();
                    System.out.println(
                            "Error in deleting object " + error.objectName() + "; " + error.message());
                }
                // 检查是否存在数据库 如果不存在则保存进入数据库
                System.out.println("完成上传，保存到数据库");
            }
            System.out.println("返回数据：" + index);
            return index + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    // 上传每个分片
    public static int uploadBigFileCore(MultipartFile file,
                                        // 分片索引
                                        Integer sliceIndex,
                                        // 切片总数
                                        Integer totalPieces,
                                        // 文件MD5
                                        String md5) throws Exception {
        // 存放目录
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(TEMP_DIR).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(TEMP_DIR).build());
        }
        // 验证文件
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(TEMP_DIR).prefix(md5.concat(FILE_SEPARATOR)).build());
        Set<String> objectNames = Sets.newHashSet();
        for (Result<Item> item : results) {
            objectNames.add(item.get().objectName());
        }
        List<Integer> indexs = Stream.iterate(0, i -> ++i)
                .limit(totalPieces)
                .filter(i -> !objectNames.contains(md5.concat(FILE_SEPARATOR).concat(Integer.toString(i))))
                .sorted()
                .collect(Collectors.toList());
        if (indexs.size() > 0) {
            if (!indexs.get(0).equals(sliceIndex)) {
                // 断点续传
                return indexs.get(0);
            }
        } else {
            return -1;
        }
        InputStream is = file.getInputStream();
        // 写入文件
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(TEMP_DIR)
                        .object(md5.concat(FILE_SEPARATOR).concat(Integer.toString(sliceIndex)))
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
        if (sliceIndex < totalPieces - 1) {
            is.close();
            return ++sliceIndex;
        } else {
            return -1;
        }
    }

    @PostMapping(value = "/download")
    public void download(@RequestBody String filename, HttpServletResponse response) {
        String parseObject = JSONObject.parseObject(filename, String.class);
        if (StringUtils.isNotBlank(parseObject)) {
            log.info("download:" + parseObject);
            try (GetObjectResponse stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket("default")
                            .object(parseObject)
                            .build());
                 BufferedInputStream bs = new BufferedInputStream(stream);
                 OutputStream os = response.getOutputStream()) {
                //设置Headers
                response.setContentType("application/octet-stream");
                //设置下载的文件的名称-该方式已解决中文乱码问题, 展示实际名字
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(parseObject.concat(".apk").getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
                byte[] buffer = new byte[1024];
                int len;
                while ((len = bs.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
