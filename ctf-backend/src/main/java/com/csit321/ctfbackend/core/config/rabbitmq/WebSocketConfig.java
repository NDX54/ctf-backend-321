package com.csit321.ctfbackend.core.config.rabbitmq;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// Configuration class for WebSocket message broker.
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Configure the message broker.
    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry config) {
        WebSocketMessageBrokerConfigurer.super.configureMessageBroker(config);
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    // Register STOMP endpoints.
    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}
