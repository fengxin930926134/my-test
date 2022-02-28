package com.fengx.mytest.external.websocket.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class WebSocketController {

    /**
     * 从前端接收websocket消息
     *
     * @param msg WebMessage
     */
    @MessageMapping("/to-one")
    public void toOne(String msg) {
        log.info(msg);
    }
}