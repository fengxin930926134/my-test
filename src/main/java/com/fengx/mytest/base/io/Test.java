package com.fengx.mytest.base.io;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Java IO类库是阻塞IO；从1.4版本开始，引进了新的异步IO库，被称为Java New IO类库，简称为JAVA NIO。
 * New IO类库的目标，就是要让Java支持非阻塞IO，基于这个原因，更多的人喜欢称Java NIO为非阻塞IO（Non-Block IO），称“老的”阻塞式Java IO为OIO（Old IO）。
 *
 * InputStream/Reader是所有输入流的基类，前者是字节输入流，后者是字符输入流
 * OutputStream/Writer是所有输出流的基类，前者是字节输出流，后者是字符输出流
 *
 * BIO: Block IO同步阻塞式IO,就是我们平常使用的传统IO,它的特点是模式简单使用方便，并发处理能力低.
 * NIO: Non IO同步非阻塞IO，是传统IO的升级，户端和服务器端通过Channel (通道)通讯，实现了多路复用。
 * AIO: Asynchronous IO是NIO的升级，也叫NIO2,实现了异步非堵塞IO，异步IO的操作基于事件和回调机制.
 */
public class Test {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        r.gc();
        // 开始时的剩余内存
        long startMem = r.totalMemory() - r.freeMemory();
        long l = System.currentTimeMillis();
        String basePath = "D:/app/source/" + File.separator + "feature" + File.separator;
//        for (int i = 0; i < 10000; i++) {
//            String path = basePath + i + ".jpg";
//            if (new File(path).exists()) {
//                System.out.println(path + "存在");
//            }
//        }
        Path folder = Paths.get(basePath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
            InputStream in;
            byte[] data;
            BASE64Encoder encoder = new BASE64Encoder();
            for (Path entry : stream) {
//                ImageToBase64ByLocal(basePath + entry.getFileName().toString());
                in = new FileInputStream(basePath + entry.getFileName().toString());
                data = new byte[in.available()];
                in.read(data);
                in.close();
                // 对字节数组Base64编码
                encoder.encode(data);
            }
        } catch (IOException ex) {
            // An I/O problem has occurred
            ex.printStackTrace();
        }
        long orz = r.totalMemory() - r.freeMemory() - startMem;
        System.out.println("使用内存：" + orz);
        System.out.println("执行时间：" + (System.currentTimeMillis() - l));
    }

    /**
     * 本地图片转换成base64字符串
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imgFile 图片本地路径
     * @return
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:40:46
     */
    public static String ImageToBase64ByLocal(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    /**
     * 名字转换
     *
     * @param sPath
     * @param tPath
     * @return
     */
    public static boolean rename(String sPath, String tPath) {
        File file1 = new File(sPath);
        File file2 = new File(tPath);
        if (file1.exists()) {
            return file1.renameTo(file2);
        } else {
            System.out.println("文件不存在！");
        }
        return false;
    }
}
