package com.fengx.mytest.base.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Java IO类库是阻塞IO；从1.4版本开始，引进了新的异步IO库，被称为Java New IO类库，简称为JAVA NIO。
 * New IO类库的目标，就是要让Java支持非阻塞IO，基于这个原因，更多的人喜欢称Java NIO为非阻塞IO（Non-Block IO），称“老的”阻塞式Java IO为OIO（Old IO）。
 */
@Slf4j
public class NIOTest {

    public static void main(String[] args) throws IOException, InterruptedException {
//        fileChannelTest();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 81));
        while (!socketChannel.finishConnect()) {
            System.out.println("执行一些操作...");
            Thread.sleep(1000);
            System.out.println("检查是否连接上...");
        }
        System.out.println("连接上...");
        Thread.sleep(1000);
    }

    /**
     * 文件复制
     */
    private static void fileChannelTest() throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("C:\\Users\\gzxzl\\Desktop\\贵职院.txt", "rw");
        FileChannel channel = accessFile.getChannel();
        RandomAccessFile accessFile2 = new RandomAccessFile("C:\\Users\\gzxzl\\Desktop\\新建文本文档 (2).txt", "rw");
        FileChannel channel2 = accessFile2.getChannel();
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        while (channel.read(buffer) != -1) {
//            buffer.flip();
////            for (int i = 0; i < buffer.limit(); i++) {
////
////                System.out.println(new String(new byte[]{buffer.get()}));
////            }
//            while (channel2.write(buffer) != 0) {
//                System.out.println("写入...");
//            }
//            buffer.compact();
//        }

        channel.transferTo(0, channel.size(), channel2);
        // 出于性能原因，操作系统不可能每次都实时将数据写入磁盘。如果需要保证写入通道的缓冲数据，最终都真正地写入磁盘，可以调用FileChannel的force()方法。
        channel2.force(true);
        channel.close();
        channel2.close();
        accessFile.close();
        accessFile2.close();
    }

    private void bufferTest() {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        buffer.put("123".getBytes());
        // 主要属性值
        log.info("buffer 属性：");
        log.info("位置position:" + buffer.position());
        log.info("可写可读limit:" + buffer.limit());
        log.info("容量capacity:" + buffer.capacity());
        // 切换读写
        buffer.flip();
        log.info("切换读写：");
        log.info("位置position:" + buffer.position());
        log.info("可写可读limit:" + buffer.limit());
        log.info("容量capacity:" + buffer.capacity());
        // 标记位置
        buffer.mark();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        log.info("位置position:" + buffer.position());
        log.info("可写可读limit:" + buffer.limit());
        log.info("容量capacity:" + buffer.capacity());
        // 回归标记位置
        buffer.reset();
        log.info("位置position:" + buffer.position());
        log.info("可写可读limit:" + buffer.limit());
        log.info("容量capacity:" + buffer.capacity());
        for (int i = 0; i < buffer.limit(); i++) {
            System.out.println(buffer.get());
        }
        // 倒带 清楚标记和位置归零
        buffer.rewind();
        for (int i = 0; i < buffer.limit(); i++) {
            System.out.println(buffer.get());
        }
        // 猜：跟buffer.compact();的区别是compact不清空数据只是把标记还原
        buffer.clear();
        log.info("位置position:" + buffer.position());
        log.info("可写可读limit:" + buffer.limit());
        log.info("容量capacity:" + buffer.capacity());
    }
}
