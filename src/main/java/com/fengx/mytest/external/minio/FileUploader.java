package com.fengx.mytest.external.minio;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import io.minio.messages.SseConfiguration;
import io.minio.messages.VersioningConfiguration;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUploader {
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      // Create a minioClient with the MinIO server playground, its access key and secret key.
      MinioClient minioClient =
          MinioClient.builder()
              .endpoint("http://127.0.0.1:9000")
              .credentials("admin", "123456789")
              .build();

      // 创建桶
      boolean found =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket("testdir").build());
      if (!found) {
        // Make a new bucket called 'asiatrip'.
        minioClient.makeBucket(MakeBucketArgs.builder().bucket("testdir").build());
      } else {

        // 获取桶加密配置
        SseConfiguration config =
                minioClient.getBucketEncryption(
                        GetBucketEncryptionArgs.builder().bucket("testdir").build());
        System.out.println(config);

        VersioningConfiguration versioningConfiguration =
                minioClient.getBucketVersioning(
                        GetBucketVersioningArgs.builder().bucket("testdir").build());
        System.out.println(versioningConfiguration.status());
      }

      // 所有桶
      List<Bucket> bucketList = minioClient.listBuckets();
      for (Bucket bucket : bucketList) {
        System.out.println(bucket.creationDate() + ", " + bucket.name());
      }

      // 删除空桶
//      minioClient.removeBucket(RemoveBucketArgs.builder().bucket("testdir").build());

      // 获取文件并转换成压缩文件
//      try (InputStream stream = minioClient.getObject(
//              GetObjectArgs.builder()
//                      .bucket("testdir")
//                      .object("favicon.ico")
//                      .build())) {
//        ZipOutputStream zipStream = null;
//        FileInputStream zipSource = null;
//        BufferedInputStream bufferStream = null;
//        try {
//          //构造最终压缩包的输出流
//          zipStream = new ZipOutputStream(new FileOutputStream(new File("C:\\Users\\gzxzl\\Desktop\\Ziptest.zip")));
//          /**
//           * 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样这里的name就是文件名,
//           * 文件名和之前的重复就会导致文件被覆盖
//           */
//          ZipEntry zipEntry = new ZipEntry("test.ico");//在压缩目录中文件的名字
//          zipStream.putNextEntry(zipEntry);//定位该压缩条目位置，开始写入文件到压缩包中
//          bufferStream = new BufferedInputStream(stream, 1024 * 10);
//          int read = 0;
//          byte[] buf = new byte[1024 * 10];
//          while ((read = bufferStream.read(buf, 0, 1024 * 10)) != -1) {
//            zipStream.write(buf, 0, read);
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//        } finally {
//          //关闭流
//          try {
//            if (null != bufferStream) bufferStream.close();
//            if (null != zipStream) {
//              zipStream.flush();
//              zipStream.close();
//            }
//            if (null != zipSource) zipSource.close();
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//        }
//      }

      // Upload
      minioClient.uploadObject(
          UploadObjectArgs.builder()
              .bucket("testdir")
              .object("tesydocx-11-23.docx")
              .filename("C:\\Users\\gzxzl\\Desktop\\tesy.docx")
              .build());

      // 下载本地
//      minioClient.downloadObject(
//              DownloadObjectArgs.builder()
//                      .bucket("testdir")
//                      .object("114dwd.png")
//                      .filename("test.png")
//                      .build());


    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
      System.out.println("HTTP trace: " + e.httpTrace());
    }
  }
}