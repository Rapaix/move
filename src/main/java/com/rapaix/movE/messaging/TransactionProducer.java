package com.rapaix.movE.messaging;

import com.rapaix.movE.config.RabbitMQConfig;
import com.rapaix.movE.dto.TransactionDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TransactionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTransaction(TransactionDTO transactionDTO) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, transactionDTO);
    }
}
