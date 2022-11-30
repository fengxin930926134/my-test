package com.fengx.mytest.external.minio;

import com.alibaba.fastjson.JSONObject;
import com.fengx.mytest.springboot.response.FailedResponse;
import com.fengx.mytest.springboot.response.ObjectResponse;
import com.fengx.mytest.springboot.response.Response;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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

    @Autowired
    private FileClient fileClient;

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
            return new ObjectResponse<>(fileClient.uploadFile(file, moduleName).object());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new FailedResponse<>();
    }

    /**
     * 按分片顺序上传
     *
     * @return 0~n 叫前端上传对应分片序号
     * -1 上传完成
     * -2 文件md5对不上
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
            // 文件秒传：查询数据库的md5如果存在则直接复制一份文件库的该文件,然后直接保存入数据库
            log.info("文件名:" + fileName + " moduleName:" + moduleName);
            // else ->
            // 上传合并
            int index = uploadBigFileCore(file,
                    Integer.parseInt(request.getParameter("sliceIndex")),
                    totalPieces,
                    md5);
            if (index == -1) {
                // 完成上传从缓存目录合并迁移到正式目录
                List<String> filenames = Stream.iterate(0, i -> ++i)
                        .limit(totalPieces)
                        .map(i -> md5.concat(FILE_SEPARATOR).concat(Integer.toString(i)))
                        .collect(Collectors.toList());
                ObjectWriteResponse response = fileClient.mergeFile(filenames, moduleName);
                // 验证md5并获取文件类型
                try (InputStream stream = fileClient.getInputStream(response.object())) {
                    String md5Hex = DigestUtils.md5Hex(stream);
                    if (!md5Hex.equals(md5)) {
                        return "-2";
                    }
                    Tika tika = new Tika();
                    String filetype = tika.detect(stream, fileName);
                    log.info("文件类型：" + filetype);
                }
                // 检查是否存在数据库 如果不存在则保存进入数据库，包括文件类型、后缀等
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
    public int uploadBigFileCore(MultipartFile file,
                                 // 分片索引
                                 Integer sliceIndex,
                                 // 切片总数
                                 Integer totalPieces,
                                 // 文件MD5
                                 String md5) throws Exception {
        // 验证文件
        List<String> objectNames = fileClient.listFileNames(fileClient.TEMP_BUCKET, md5);
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
        // 写入文件
        fileClient.uploadFile(file, fileClient.TEMP_BUCKET, md5.concat(fileClient.FILE_SEPARATOR).concat(Integer.toString(sliceIndex)));
        if (sliceIndex < totalPieces - 1) {
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

    @GetMapping(value = "/downloadSlice")
    public void downloadSlice(@RequestParam String filename,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        if (StringUtils.isNotBlank(filename)) {
            log.info("download:" + filename);
            String range = request.getHeader("Range");
            if (StringUtils.isNotBlank(range)) {
                log.info("切片下载");
                StatObjectResponse statObjectResponse = fileClient.getFileInfo("default".concat(FILE_SEPARATOR).concat(filename));
                System.out.println(statObjectResponse);
                // 分片
                log.info("current request rang:" + range);
                //开始下载位置
                long startByte = 0;
                //结束下载位置
                long endByte = statObjectResponse.size() - 1;
                log.info("文件开始位置：{}，文件结束位置：{}，文件总长度：{}", startByte, endByte, statObjectResponse.size());

                //有range的话
                if (range.contains("bytes=") && range.contains("-")) {
                    range = range.substring(range.lastIndexOf("=") + 1).trim();
                    String[] ranges = range.split("-");
                    try {
                        //判断range的类型
                        if (ranges.length == 1) {
                            //类型一：bytes=-2343
                            if (range.startsWith("-")) {
                                endByte = Long.parseLong(ranges[0]);
                            }
                            //类型二：bytes=2343-
                            else if (range.endsWith("-")) {
                                startByte = Long.parseLong(ranges[0]);
                            }
                        }
                        //类型三：bytes=22-2343
                        else if (ranges.length == 2) {
                            startByte = Long.parseLong(ranges[0]);
                            endByte = Long.parseLong(ranges[1]);
                        }

                    } catch (NumberFormatException e) {
                        startByte = 0;
                        endByte = statObjectResponse.size() - 1;
                        log.error("Range Occur Error, Message:" + e.getLocalizedMessage());
                    }
                }

                //要下载的长度
                long contentLength = endByte - startByte + 1;
                //文件类型
                String contentType = request.getServletContext().getMimeType(filename);

                // 解决下载文件时文件名乱码问题
                byte[] fileNameBytes = filename.getBytes(StandardCharsets.UTF_8);
                filename = new String(fileNameBytes, 0, fileNameBytes.length, StandardCharsets.ISO_8859_1);

                //各种响应头设置
                //支持断点续传，获取部分字节内容：
                response.setHeader("Accept-Ranges", "bytes");
                //http状态码要为206：表示获取部分内容
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                response.setContentType(contentType);
                response.setHeader("Last-Modified", statObjectResponse.lastModified().toString());
                //inline表示浏览器直接使用，attachment表示下载，fileName表示下载的文件名
                response.setHeader("Content-Disposition", "inline;filename=" + filename);
                response.setHeader("Content-Length", String.valueOf(contentLength));
                // Content-Range，格式为：[要下载的开始位置]-[结束位置]/[文件总大小]
                response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + statObjectResponse.size());

                //已传送数据大小
                long transmitted = 0;
                try {
                    InputStream stream = fileClient.getInputStream(statObjectResponse.object(), startByte, contentLength);
                    BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = stream.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                    os.flush();
                    os.close();
                    response.flushBuffer();
                    log.info("下载完毕：" + startByte + "-" + endByte + "：" + transmitted);
                } catch (ClientAbortException e) {
                    log.warn("用户停止下载：" + startByte + "-" + endByte + "：" + transmitted);
                    //捕获此异常表示拥护停止下载
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("用户下载IO异常，Message：{}", e.getLocalizedMessage());
                }
            } else {
                // 直接下载
                log.info("直接下载");
                try (InputStream stream = fileClient.getInputStream("default".concat(FILE_SEPARATOR).concat(filename));
                     BufferedInputStream bs = new BufferedInputStream(stream);
                     OutputStream os = response.getOutputStream()) {
                    //设置Headers
                    response.setContentType("application/octet-stream");
                    //设置下载的文件的名称-该方式已解决中文乱码问题, 展示实际名字
                    response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bs.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                    os.flush();
                    os.close();
                    response.flushBuffer();
                } catch (ClientAbortException e) {
                    log.warn("用户停止下载");
                    //捕获此异常表示拥护停止下载
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("用户下载IO异常，Message：{}", e.getLocalizedMessage());
                }
            }
        }
    }
}
