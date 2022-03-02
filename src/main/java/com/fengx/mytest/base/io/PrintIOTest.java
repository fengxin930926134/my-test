package com.fengx.mytest.base.io;

import java.io.*;

/**
 * PrintStream继承自OutputStream,属于字节流的一种,方法包含写入单个字节和字节数组的方法.相似流有PrintWriter,继承自Writer()方法,属于字符流的一种.PrintWriter流中没有写入字节的方法,而有写入单个字符和字符数组的方法.
 *
 *  PrintWriter给人一种可以直接对文件进行操作的假象,PW是一个高级流(自动行刷新),实际上PW包装了字节流(负责写字节)、字符流(负责读字符)和字符缓冲流(负责提高效率)。PrintWriter创建时如果有参数true时，会有行刷新。PrintWriter调用println()方法有行刷新。
 */
public class PrintIOTest {

    public static void main(String[] args) throws IOException {
        final String fileName = "C:\\Users\\gzxzl\\Desktop\\aa.txt";
        File file = new File(fileName);
        // PrintStream
        testPrintMethod(fileName, file);
        testOtherMethod(fileName,file);
        // PrintWriter
        PrintWriter pw = new PrintWriter(fileName);
        pw.println("啊哈哈哈哈哈");
        pw.println("飞雪连天射白鹿");
        System.out.println("写出完毕！");
        pw.close();
    }

    private static void testOtherMethod(String fileName,File file) throws IOException {
        PrintStream ps = new PrintStream(fileName);
        ps.write("helloworld".getBytes());
        ps.println();
        ps.format("文件名称:%s", file.getName());
        ps.println();
        ps.write(0x41);
        ps.append("abcde");
        ps.close();

    }

    private static void testPrintMethod(final String fileName, File file) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream(fileName));
        ps.println('a');
        ps.println("hello");
        ps.println(2345);
        ps.print(3.1415);
        ps.println();//写入换行符.
        ps.printf("文件名称:%s,是否可读:%s", file.getName(),file.canRead());
        ps.println();
        ps.close();
    }
}
