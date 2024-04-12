package com.sky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置类，用于注册WebSocket的Bean
 * WebSocketConfiguration类包含了一个@Bean方法，用于注册ServerEndpointExporter。
 * ServerEndpointExporter用于扫描ServerEndpoint配置类，并将其注册为WebSocket端点。
 */
@Configuration
public class WebSocketConfiguration {
    /**
     * 注册ServerEndpointExporter Bean
     *
     * @return ServerEndpointExporter对象
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
