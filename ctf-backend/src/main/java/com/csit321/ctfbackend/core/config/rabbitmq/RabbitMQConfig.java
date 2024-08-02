package com.csit321.ctfbackend.core.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitMQConfig {

    public static final String SCORE_UPDATE_QUEUE_NAME = "scoreUpdates";

    @Bean
    public SimpleMessageConverter converter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("com.csit321.ctfbackend.room.dto.internal.*", "java.util.*"));
        return converter;
    }

    @Bean
    public Queue scoreUpdateQueue() {
        return new Queue(SCORE_UPDATE_QUEUE_NAME, false);
    }

}
