//package com.fengx.mytest.external.websocket;
//
//import com.fengx.mytest.external.rocketmq.JmsConfig;
//import com.fengx.mytest.external.rocketmq.RQProducer;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.exception.MQBrokerException;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.remoting.exception.RemotingException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * websocket整合消息队列测试
// *
// * javax.websocket.server.ServerContainer not available
// * SpringBootTest在启动的时候不会启动服务器，所以WebSocket自然会报错，这个时候需要添加选项webEnvironment，以便提供一个测试的web环境。
// */
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TestApp {
//
////    @Autowired
////    private AmqpTemplate template;
////
////    /**
////     * 测试rabbitMQ
////     */
////    @Test
////    public void sendDirectMsg() {
////        template.convertAndSend("socketQueue", "理论上这里会区分一下类型");
////    }
//
//    @Autowired
//    private RQProducer producer;
//    /**
//     * 测试rocketMQ
//     */
//    @Test
//    public void sendDirectMsg() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
//        //创建生产信息
//        Message message = new Message(JmsConfig.TOPIC, "testtag", "这是消息".getBytes());
//        //发送
//        SendResult sendResult = producer.getProducer().send(message);
//        log.info(sendResult.toString());
//    }
//}