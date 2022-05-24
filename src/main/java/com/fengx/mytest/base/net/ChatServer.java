package com.fengx.mytest.base.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private ServerSocket server;//创建网络服务端
    private Set<Socket> sockets;//创建数组

    public ChatServer() {
        ExecutorService pool= Executors.newCachedThreadPool();//线程池
        try {
            sockets=new HashSet<>();//HashSet实现了Set接口，它不允许集合中有重复的值
            server=new ServerSocket(9999);//实例化对象
            while (true){
                Socket socket=server.accept();//调用accept()方法，返回Socket对象
                String cip=socket.getInetAddress().getHostAddress();//获取IP地址
                System.out.println(cip);
                sockets.add(socket);
                pool.execute(new Runnable() {//启动线程池
                    @Override
                    public void run() {
                        try {
                            InputStreamReader isr=new InputStreamReader(socket.getInputStream());//读出
                            BufferedReader br=new BufferedReader(isr);
//                            String ip=socket.getInetAddress().getHostAddress();
//                            System.out.println("==>"+ip);
                            while (true){
                                String m=br.readLine();
                                //System.out.println(ip+"说："+m);
                                for (Socket so:sockets){//写一个循环函数
                                    PrintWriter  out=new PrintWriter(so.getOutputStream());
                                    out.write(cip+"说："+m+"\n");//加换行
                                    out.flush();
                                }
                                System.out.println("转发完成！");
                            }
                        } catch (IOException e) {
                            //e.printStackTrace();//可能出现连接重置
                            sockets.remove(socket);
                        }
                    }
                });
            }
        } catch (IOException e) {
            System.out.println("端口被占用！");
        }
    }

    public static void main(String[] agrs){
        new ChatServer();
    }
}
