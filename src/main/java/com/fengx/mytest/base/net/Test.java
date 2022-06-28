package net;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 * 1、实例化一个Socket对象，向服务器发出连接请求
 */
public class Test {
    public static void main(String[] agrs){
        try {
            Socket socket=new Socket("192.168.1.18",9001);
            System.out.println("与客户端连接成功");

            InputStreamReader isr=new InputStreamReader(socket.getInputStream());
            //是字节流与字符流之间的桥梁，能将字节流输出为字符流，并且能为字节流指定字符集，可输出一个个的字符；
            BufferedReader br=new BufferedReader(isr);//提供通用的缓冲方式文本读取
            String s=br.readLine();
            System.out.println(s);
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
