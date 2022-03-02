package com.fengx.mytest.base.io;

import java.io.*;

/**
 * DataInputStream 是数据输入流。它继承于FilterInputStream。
 * DataInputStream 是用来装饰其它输入流，它“允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型”。应用程序可以使用DataOutputStream(数据输出流)写入由DataInputStream(数据输入流)读取的数据。
 *
 */
public class DataIOTest {

    public static void main(String[] args) throws IOException {


        DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:\\Users\\gzxzl\\Desktop\\aa.txt"));
        dos.writeChar('A');
        dos.writeChar('\0');
        dos.flush();
        dos.close();

        DataInputStream dis = new DataInputStream(new FileInputStream("C:\\Users\\gzxzl\\Desktop\\aa.txt"));
//        System.out.println(dis.readDouble());
        System.out.println(dis.readChar());
        System.out.println(dis.readBoolean());
        dis.close();
    }
}
