package com.fengx.mytest.base.io;

import java.io.*;

/**
 * BufferedReader和BufferedWriter为默认带有缓冲的字符输出输入流，因为有缓冲区所以效率比没有缓冲区的高。
 *
 */
public class BufferedIOTest {

    public static void main(String[] args) throws IOException {
//        System.out.println(getString());
//        setString("BufferedWriter");

        // BufferedInputStream/BufferedOutputStream
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\gzxzl\\Desktop\\aa.txt"));
        byte[] buffer = new byte[1024];
        while (bis.read(buffer, 0, buffer.length) != -1) {
            System.out.println(new String(buffer));
        }
    }

    private static void setString(String text) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\gzxzl\\Desktop\\aa.txt")));
        bw.append(text);
        bw.write(text);
        bw.append(text);
        bw.flush();
        bw.close();
    }

    private static String getString() throws IOException {
        // System.in 读取键盘上的数据
        // InputStreamReader 将字节流向字符流的转换
        InputStreamReader isr = new InputStreamReader(System.in); // 读取
        InputStreamReader isf = new InputStreamReader(new FileInputStream("C:\\Users\\gzxzl\\Desktop\\aa.txt"));
        // 创建字符流缓冲区
        BufferedReader br = new BufferedReader(isf); // 缓冲
        // 读取一个文本行
        String s = br.readLine(); // 阻塞式，当没有数据读取时，就一直会阻塞，而不是返回null
        return s;
    }
}
