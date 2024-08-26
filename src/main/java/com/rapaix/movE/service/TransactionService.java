package com.rapaix.movE.service;

import com.rapaix.movE.entity.Transaction;
import com.rapaix.movE.entity.User;
import com.rapaix.movE.exception.TransactionNotFoundException;
import com.rapaix.movE.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    @Transactional
    public Transaction createTransaction(Long fromUserId, Long toUserId, BigDecimal amount) {
        logger.info("Creating transaction for from user {} to user {}", fromUserId, toUserId);
        User fromUser = userService.getUserById(fromUserId);
        User toUser = userService.getUserById(toUserId);

        userService.updateBalance(fromUserId, amount.negate());
        userService.updateBalance(toUserId, amount);

        Transaction transaction = new Transaction();
        transaction.setFromUser(fromUser);
        transaction.setToUser(toUser);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        logger.info("Retrieving all transactions");
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        logger.info("Retrieving transaction by id {}", id);
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
    }
}
