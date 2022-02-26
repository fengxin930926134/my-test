//package com.fengx.mytest.external.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * 消费者 一个消息只会被一个消费者监听
// */
//@Component
//public class Consumer {
//
//    @RabbitListener(queues = "cord")
//    //@RabbitListener(queues = "cord", containerFactory="myFactory")
//    public void processMessage(String msg) {
//        System.out.format("Receiving Message: -----[%s]----- .\n", msg);
//    }
//}