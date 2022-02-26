//package com.fengx.mytest.external.rabbitmq;
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 交换机有四种类型:
// *
// * 路由模式(Direct)：
// *
// * ​direct 类型的行为是"先匹配, 再投送"。即在绑定时设定一个 routing_key, 消息的routing_key 匹配时, 才会被交换器投送到绑定的队列中去。direct是rabbitmq的默认交换机类型。
// *
// * 通配符模式(Topic)：
// *
// * ​类似路由模式，但是routing_key支持模糊匹配，按规则转发消息（最灵活）。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
// *
// * 发布订阅模式(Fanout)：
// *
// * ​转发消息到所有绑定队列，忽略routing_key。
// *
// * Headers:
// *
// * ​ 设置header attribute参数类型的交换机。相较于 direct 和 topic 固定地使用 routing_key , headers 则是一个自定义匹配规则的类型，忽略routing_key。在队列与交换器绑定时, 会设定一组键值对规则, 消息中也包括一组键值对( headers 属性), 当这些键值对有一对, 或全部匹配时, 消息被投送到对应队列。
// * ​ 在绑定Queue与Exchange时指定一组键值对，当消息发送到RabbitMQ时会取到该消息的headers与Exchange绑定时指定的键值对进行匹配。如果完全匹配则消息会路由到该队列，否则不会路由到该队列。headers属性是一个键值对，可以是Hashtable，键值对的值可以是任何类型。
// *
// * 匹配规则x-match有下列两种类型：
// *
// * x-match = all ：表示所有的键值对都匹配才能接受到消息
// * x-match = any ：表示只要有键值对匹配就能接受到消息
// */
//@Configuration
//public class RabbitMQConfig {
//
//    //声明队列 一个消息只会被一个消费者监听
//    @Bean
//    public Queue cordqueue() {
//        // MQ生产者创建队列时，属性exclusive设置成true会导致监听不到队列。exclusive：是否排外的，有两个作用，一：当连接关闭时connection.close()该队列是否会自动删除；二：该队列是否是私有的private，如果不是排外的，可以使用两个消费者都访问同一个队列，没有任何问题，如果是排外的，会对当前队列加锁，其他通道channel是不能访问的，如果强制访问会报异常。
//        // durable表示持久化属性
//        //Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
//        return new Queue("cord", false, false, true);
//    }
//
//    @Bean
//    public Queue socketQueue(){
//        return new Queue("socketQueue");
//    }
//
////    //声明Topic交换机
////    @Bean
////    TopicExchange topicExchange() {
////        return new TopicExchange(topicExchangeName);
////    }
////
////    //将队列与Topic交换机进行绑定，并指定路由键
////    @Bean
////    Binding topicBinding(Queue queue, TopicExchange topicExchange) {
////        return BindingBuilder.bind(queue).to(topicExchange).with("org.cord.#");
////    }
////
////    //声明fanout交换机
////    @Bean
////    FanoutExchange fanoutExchange() {
////        return new FanoutExchange(fanoutExchange);
////    }
////
////    //将队列与fanout交换机进行绑定
////    @Bean
////    Binding fanoutBinding(Queue queue, FanoutExchange fanoutExchange) {
////        return BindingBuilder.bind(queue).to(fanoutExchange);
////    }
//
////    //声明Headers交换机
////    @Bean
////    HeadersExchange headersExchange() {
////        return new HeadersExchange(headersExchange);
////    }
////
////    //将队列与headers交换机进行绑定
////    @Bean
////    Binding headersBinding(Queue queue, HeadersExchange headersExchange) {
////        Map<String, Object> map = new HashMap<>();
////        map.put("First","A");
////        map.put("Fourth","D");
////        //whereAny表示部分匹配，whereAll表示全部匹配
//////        return BindingBuilder.bind(queue).to(headersExchange).whereAll(map).match();
////        return BindingBuilder.bind(queue).to(headersExchange).whereAny(map).match();
////    }
//}