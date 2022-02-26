package com.fengx.mytest.external.websocket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket简单整合消息队列
 */
@Data
@Slf4j
// 把当前类标识成一个WebSocket的服务端，值是访问的URL地址
@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private static CopyOnWriteArraySet<WebSocketServer> sessions = new CopyOnWriteArraySet<WebSocketServer>();

//    /**
//     * 监听队列，从rabbitMQ队列中把刚发送的消息取出来
//     * @param message
//     * @param channel
//     * @param tag
//     * @throws Exception
//     */
//    @RabbitListener(queues = "socketQueue")
//    public void getMessAge(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
//
//        log.info("发来消息：" + message);
//        //  确认收到--消费  不需要确认 因为是配置自动确认
//        // channel.basicAck(tag, false);
//
//        //用来判断session中是否存在数据
//        if (sessions.size() != 0) {
//            for (WebSocketServer s : sessions) {
//                if (s != null) {
//                    //向已连接客户端发送信息
//                    s.session.getBasicRemote().sendText(message);
//                }
//            }
//        }
//    }


    /**
     * 连接成功
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //这个一定要写，第一次很容易忽略！
        sessions.add(this);
        log.info("[WebSocket] 连接成功，当前连接人数为：={}", sessions.size());

    }

    /**
     * 连接断开
     */
    @OnClose
    public void onClose() {
        //释放
        sessions.remove(this);
        log.info("[WebSocket] 退出成功，当前连接人数为：={}", sessions.size());
    }

    /**
     * 收到消息
     */
    @OnMessage
    public String onMessage(String message) {
        log.info("[WebSocket] 收到消息：{}", message);

        return "你已成功连接，这是webSocket服务端的返回信息！";
    }

}