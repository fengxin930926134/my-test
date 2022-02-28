package com.fengx.mytest.external.websocket.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimpleWebSocketServer {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 发送公告
     *
     * @param msg WebMessage
     */
    public void toAll(String msg) {
        template.convertAndSend("/topic/to-all", msg);
    }

    /**
     * 发送指定用户
     *
     * @param msg WebMessage
     */
    public void toOne(String msg) {
        template.convertAndSendToUser("123", "/to-one", msg);
    }
}
