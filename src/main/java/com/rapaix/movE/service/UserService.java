package com.rapaix.movE.service;

import com.rapaix.movE.entity.User;
import com.rapaix.movE.exception.InsufficientBalanceException;
import com.rapaix.movE.exception.UserNotFoundException;
import com.rapaix.movE.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user) {
        logger.info("Creating new user: {}", user.getUsername());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        logger.info("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });
    }

    @Transactional
    public void updateBalance(Long userId, BigDecimal amount) {
        logger.info("Updating balance for user id: {}. Amount: {}", userId, amount);
        User user = getUserById(userId);
        BigDecimal newBalance = user.getBalance().add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            logger.error("Insufficient balance for user: {}. Current balance: {}, Attempted withdrawal: {}",
                    userId, user.getBalance(), amount.abs());
            throw new InsufficientBalanceException("Insufficient balance for user: " + userId);
        }
        user.setBalance(newBalance);
        userRepository.save(user);
        logger.info("Balance updated successfully for user id: {}. New balance: {}", userId, newBalance);

    }
}

