package com.fengx.mytest.external.minio;

import com.alibaba.fastjson.JSONObject;
import com.fengx.mytest.springboot.response.FailedResponse;
import com.fengx.mytest.springboot.response.ObjectResponse;
import com.fengx.mytest.springboot.response.Response;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

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

    @PostConstruct
    public void init() {
        minioClient = MinioClient.builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials("admin", "123456789")
                .build();
    }

    @PostMapping(value = "/upload")
    public Response upload(@RequestParam("file") MultipartFile file, String moduleName) {
        if (StringUtils.isBlank(moduleName)) {
            moduleName = "default";
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
