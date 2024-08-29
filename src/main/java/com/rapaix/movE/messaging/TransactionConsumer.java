package com.rapaix.movE.messaging;

import com.rapaix.movE.config.RabbitMQConfig;
import com.rapaix.movE.dto.TransactionDTO;
import com.rapaix.movE.service.TransactionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {
    private final TransactionService transactionService;

    @Autowired
    public TransactionConsumer(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveTransaction(TransactionDTO transactionDTO) {
        transactionService.createTransaction(
                transactionDTO.fromUserId(),
                transactionDTO.toUserId(),
                transactionDTO.amount()
        );
    }}
