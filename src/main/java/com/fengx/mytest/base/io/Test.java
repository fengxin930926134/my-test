package com.fengx.mytest.base.io;

import java.io.File;

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
