package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.config.rabbitmq.RabbitMQConfig;
import com.csit321.ctfbackend.room.dto.internal.ScoreUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreUpdateService {

    private final RabbitTemplate rabbitTemplate;
    private final SimpMessagingTemplate messagingTemplate;

    public void sendScoreUpdate(ScoreUpdateDTO scoreUpdateDTO) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.SCORE_UPDATE_QUEUE_NAME, scoreUpdateDTO);
    }

}
