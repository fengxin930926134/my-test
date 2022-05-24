package com.fengx.mytest.base.net;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 * 1、实例化一个Socket对象，向服务器发出连接请求
 */
public class Client {
    public static void main(String[] agrs){
        try {
            Socket socket=new Socket("192.168.1.18",9988);
            System.out.println("与客户端连接成功");
            new Receiver(socket).start();
            OutputStreamWriter osw=new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bw=new BufferedWriter(osw);
            Scanner kb=new Scanner(System.in);
            String mess=kb.nextLine();
//            InputStreamReader isr=new InputStreamReader(socket.getInputStream());
//            BufferedReader br=new BufferedReader(isr);
            while (!mess.equals("bye")){
                osw.write(mess+"\n");//加回车
                osw.flush();//刷新
//                String s=br.readLine();
//                System.out.println("服务器说："+s);
                mess=kb.nextLine();
            }
            kb.close();osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
