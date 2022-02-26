package com.fengx.mytest.base.nio;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * 使用DatagramChannel数据包通道发送数据的实践案例
 */
@Slf4j
public class UDPTest {
    public static void main(String[] args) throws InterruptedException {
        new Thread(UDPTest::UDPServer).start();
        Thread.sleep(1000);
        UDPTest.UDPClientSend("hhahaha哈哈");
    }

    private static void UDPClientSend(String content) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            // 非阻塞
            channel.configureBlocking(false);
            log.info("启动客户端...");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(content.getBytes());
            buffer.flip();
            log.info("发送消息...");
            channel.send(buffer, new InetSocketAddress("127.0.0.1", 8087));
            buffer.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("完成");
    }

    private static void UDPServer() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try (DatagramChannel channel = DatagramChannel.open()) {
            // 非阻塞
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress("127.0.0.1",8087));
            log.info("启动服务端...");
            // 通道选择器
            Selector selector = Selector.open();
            // 注册到通道
            channel.register(selector, SelectionKey.OP_READ);
            // 通过选择器查询IO
            while (selector.select() > 0) {
                // 迭代IO
                for (SelectionKey key: selector.keys()) {
                    // 可读事件，有数据到来
                    if (key.isReadable()) {
                        channel.receive(buffer);
                        buffer.flip();
                        log.info("接收：" + new String(buffer.array(), 0, buffer.limit()));
                        buffer.clear();
                    }
                }
            }
            selector.close();
            log.info("关闭服务端...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
