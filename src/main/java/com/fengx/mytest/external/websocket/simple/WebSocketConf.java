package com.fengx.mytest.external.websocket.simple;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConf implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 配置客户端接收消息 订阅Broker名称
        config.enableSimpleBroker("/topic", "/user");
        // 配置客户端发送消息 全局使用的订阅前缀(客户端订阅路径上会体现出来)
        config.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀(客户端订阅路径上会体现出来)，不设置的话，默认也是/user/
        // 注意:enableSimpleBroker方法里的某个参数路径必须和该方法的路径要一样，不然指定用户发送消息将会失败
        config.setUserDestinationPrefix("/user/");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 客户端与服务器端建立连接的点
        registry.addEndpoint("/any-socket")
                // 允许跨域
//                .setAllowedOrigins("*")
                .setAllowedOriginPatterns("*")
                // 允许使用sockJs方式访问
                .withSockJS();
    }
}
