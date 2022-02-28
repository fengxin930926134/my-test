package com.fengx.mytest.external.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试用例
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestApp {

    @Autowired
    private AQProducer producer;

    //Direct
    @Test
    public void sendDirectMsg() {
        producer.sendDirectMsg("cord", "direct是rabbitmq的默认交换机类型。");
    }

    //Topic
    @Test
    public void sendtopicMsg() {
        producer.sendExchangeMsg("topic-exchange","org.cord.test", "hello world");
    }

    //Fanout
    @Test
    public void sendFanoutMsg() {
        producer.sendExchangeMsg("fanout-exchange", "abcdefg", String.valueOf(System.currentTimeMillis()));
    }

    //Headers
    @Test
    public void sendHeadersMsg() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("First","A");
        producer.sendHeadersMsg("headers-exchange", "hello word", map);
    }
}
