package com.fengx.mytest.base.net;

/**
 * 服务端
 * 1、实例化一个ServerSocket对象
 * 2、调用accept()方法，等待客户端的请求
 *    若接受到客户端请求建立了连接，则返回一个Socket对象
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] agrs){
        try {
            ServerSocket server=new ServerSocket(9988);//实例化对象
            System.out.println("服务器已启动！");
            Socket socket=server.accept();//调用accept()方法，返回Socket对象
            System.out.println("与客户端建立了联系!");
            //new Receiver(socket,"客户端").start();//启动接收线程
            String cip=socket.getInetAddress().getHostAddress();//当前客户端的ip地址
            System.out.println("IP"+cip);//获取ip
            System.out.println("port:"+socket.getPort());//获取客户端口
            //键盘输入
            Scanner kb=new Scanner(System.in);
            PrintWriter pw=new PrintWriter(socket.getOutputStream());
            String m=kb.nextLine();
            while (!m.equals("bye")){
                pw.write(m+"\n");
                pw.flush();
                m=kb.nextLine();
            }
            pw.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("端口被占用！");
        }
    }
}
