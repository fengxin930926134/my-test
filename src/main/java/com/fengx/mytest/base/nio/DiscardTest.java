package com.fengx.mytest.base.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

/**
 * Discard服务器的功能很简单：仅仅读取客户端通道的输入数据，读取完成后直接关闭客户端通道；并且读取到的数据直接抛弃掉（Discard）。
 */
public class DiscardTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    startNioDiscardServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        startClient();
    }

    public static void startClient() throws IOException {
        InetSocketAddress address = new InetSocketAddress ("127.0.0.1", 7979) ;
        // 1.获取通道
        SocketChannel socketChannel = SocketChannel.open(address);
        // 2.切换成非阻塞模式
        socketChannel.configureBlocking(false) ;
        //不断地自旋、等待连接完成，或者做-些其他的事情
        while (!socketChannel.finishConnect()) {

        }
        System.out.println("客户端连接成功");
        // 3.分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer. allocate(1024) ;
        byteBuffer.put("hello world". getBytes());
        byteBuffer.flip();
        //发送到服务器
        socketChannel.write (byteBuffer);
        socketChannel.shutdownOutput() ;
        socketChannel.close() ;
    }

    private static void startNioDiscardServer() throws IOException {
        // 获取选择器
        Selector selector = Selector.open();
        // 获取通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 绑定连接
        socketChannel.bind(new InetSocketAddress(7979));
        System.out.println("服务器启动完成");
        // 将通道注册到选择器上
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮询IO就绪事件
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key:selectionKeys) {
                // 判断key是什么事件
                if (key.isAcceptable()) {
                    // 连接就绪事件 获取客户端连接
                    SocketChannel channel = socketChannel.accept();
                    channel.configureBlocking(false);
                    // 将该新连接的通道的可读事件注册到选择器上
                    channel.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    System.out.println("进来了");
                    // 可读事件
                    SocketChannel channel = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (channel.read(buffer) > 0) {
                        buffer.flip();
                        byte[] array = buffer.array();
                        System.out.println(new String(array));
                        buffer.clear();
                    }
                    channel.close();
                }

                key.cancel();
            }
        }
        socketChannel.close();
    }
}
