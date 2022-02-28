//package com.fengx.mytest.external.websocket;
//
//import com.fengx.mytest.external.websocket.simple.SimpleWebSocketServer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * 用作websocket的消息发送解耦 RabbitMQ
// */
//@Slf4j
//@Component
//public class AQConsumerByWebSocket {
//
//    @Autowired
//    private SimpleWebSocketServer simpleWebSocketServer;
//
//    @RabbitListener(queues = "socketQueue")
//    public void processMessage(String msg) {
//        log.info("Receiving Message: -----{}----- .\n", msg);
//        // 原生发送消息
//        // WebSocketServer.broadCastMessage(msg);
//        // 消息代理发送消息
//        simpleWebSocketServer.toOne("这是单个");
//        simpleWebSocketServer.toAll("这是全部");
//    }
//}
