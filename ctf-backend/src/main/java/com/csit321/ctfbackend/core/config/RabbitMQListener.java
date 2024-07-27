package com.csit321.ctfbackend.core.config;

import com.csit321.ctfbackend.room.dto.internal.ScoreUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private final SimpMessagingTemplate template;

    @RabbitListener(queues = RabbitMQConfig.SCORE_UPDATE_QUEUE_NAME)
    public void listen(ScoreUpdateDTO scoreUpdateDTO) {
        template.convertAndSend("/topic/scores", scoreUpdateDTO);
    }

}
