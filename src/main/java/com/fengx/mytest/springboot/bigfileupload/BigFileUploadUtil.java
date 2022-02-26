package com.fengx.mytest.springboot.bigfileupload;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description 文件分片上传、断点续传、文件秒传的工具类
 */
public class BigFileUploadUtil {
    private static final String TEMP_DIR = "D:\\data";

    public static int getBigFileProgress(String filename, int totalPieces, String key) {
        // 目录
        String tempDir = TEMP_DIR + File.separator + file2md5(filename + key);
        // 定义合并后的文件位置
        String url = tempDir + File.separator + filename;
        File file = new File(url);
        if (!file.exists()) {
            File dir = new File(tempDir);
            if (dir.isDirectory()) {
                for (int i = 0; i < totalPieces; i++) {
                    // 检查当前上传进度
                    File fileFragment = new File(url + i);
                    if (!fileFragment.exists()) {
                        return i;
                    }
                }
                // 还未合并返回最后一个分片
                return (totalPieces - 1);
            } else {
                // 返回上传第一个分片的指令
                return 0;
            }
        } else {
            // 已上传完成
            return -1;
        }
    }

    public static int uploadBigFile(MultipartFile file,
                                    HttpServletRequest request) throws IOException, InterruptedException {
        // 文件名 test.jpg
        String fileName = request.getParameter("fileName");
        // 分片索引
        String sliceIndex = request.getParameter("sliceIndex");
        // 切片总数
        int totalPieces = Integer.parseInt(request.getParameter("totalPieces"));
        // 前端生成秘钥(名字+文件大小+分片大小 并加密)
        String key = "123124";
        // 存放目录
        String tempDir = TEMP_DIR + File.separator + file2md5(fileName + key);
        //写文件分片到临时目录
        InputStream is = file.getInputStream();
        int index = Integer.parseInt(sliceIndex);
        // 断点续传、文件秒传
        int bigFileProgress = getBigFileProgress(fileName, totalPieces, key);
        if (bigFileProgress != index) {
            return bigFileProgress;
        }
        // 写入文件
        File file1 = new File(tempDir + File.separator + fileName + sliceIndex);
        if (!file1.exists()) {
            FileUtils.copyInputStreamToFile(is, file1);
        }
        if (index < totalPieces - 1) {
            is.close();
            return ++index;
        } else {
            //文件的最后一个分片，写完此分片之后开始合并文件
            OutputStream os = new BufferedOutputStream(new FileOutputStream(tempDir + File.separator + fileName));
            //对临时目录的所有文件分片进行遍历，进行合并
            for (int i = 0; i < totalPieces; i++) {
                // 检查文件
                File tempFile = new File(tempDir + File.separator + fileName + i);
                if (!tempFile.exists()) {
                    return i;
                }
                byte[] bytes = FileUtils.readFileToByteArray(tempFile);
                os.write(bytes);
                os.flush();
            }

            os.close();
            is.close();
            //删除所有的分片文件，只保留合并后的文件
            File file2 = new File(tempDir);
            File[] files = file2.listFiles();
            for (File file3 : files) {
                String name = file3.getName();
                if (!name.equals(fileName)) {
                    file3.delete();
                }
            }
            return -1;
        }
    }

    /**
     * 通过文件生成md5，用来指定临时文件目录（每个大文件的上传都独立于不同的目录）
     */
    private static String file2md5(String fileInfo) {
        //得到md5的实例
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("生成md5失败");
            return "default";
        }
        //update的是byte类型的数组
        md.update(fileInfo.getBytes());
        //加密后返回
        byte[] r = md.digest();
        return String.format("%032x", new BigInteger(1, r));
    }
}
