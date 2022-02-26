package com.fengx.mytest.external.websocket;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * javax.websocket.server.ServerContainer not available
 * SpringBootTest在启动的时候不会启动服务器，所以WebSocket自然会报错，这个时候需要添加选项webEnvironment，以便提供一个测试的web环境。
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestApp {

    @Autowired
    private AmqpTemplate template;

    /**
     * 测试rabbitMQ
     */
    @Test
    public void sendDirectMsg() {
        template.convertAndSend("socketQueue", "direct是rabbitmq的默认交换机类型。");
    }
}