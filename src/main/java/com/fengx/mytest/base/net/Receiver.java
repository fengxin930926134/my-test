package com.fengx.mytest.base.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
/*
 *创建接收的一个线程
 */
public class Receiver extends Thread{
    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {//收
            InputStreamReader isr=new InputStreamReader(socket.getInputStream());
            //是字节流与字符流之间的桥梁，能将字节流输出为字符流，并且能为字节流指定字符集，可输出一个个的字符；
            BufferedReader br=new BufferedReader(isr);//提供通用的缓冲方式文本读取
            String s=br.readLine();
            //readLine读取一个文本行， 从字符输入流中读取文本，缓冲各个字符，从而提供字符、数组和行的高效读取。
            while (!s.equals("bye")){
                System.out.println(s);
                s=br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
