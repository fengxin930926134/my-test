package com.fengx.mytest.external.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * 生产者
 */
@Component
public class AQProducer {

    @Autowired
    private AmqpTemplate template;

    @Autowired
    private AmqpAdmin admin;

    /**
     * @param routingKey 路由关键字
     * @param msg 消息体
     */
    public void sendDirectMsg(String routingKey, String msg) {
        template.convertAndSend(routingKey, msg);
    }

    /**
     * @param routingKey 路由关键字
     * @param msg 消息体
     * @param exchange 交换机
     */
    public void sendExchangeMsg(String exchange, String routingKey, String msg) {
        template.convertAndSend(exchange, routingKey, msg);
    }

    /**
     * @param map 消息headers属性
     * @param exchange 交换机
     * @param msg 消息体
     */
    public void sendHeadersMsg(String exchange, String msg, Map<String, Object> map) {
        template.convertAndSend(exchange, null, msg, message -> {
            message.getMessageProperties().getHeaders().putAll(map);
            return message;
        });
    }
}