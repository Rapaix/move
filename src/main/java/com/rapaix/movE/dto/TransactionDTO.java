package com.rapaix.movE.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record TransactionDTO(
        Long id,

        @NotNull(message = "From user ID is required")
        @Positive(message = "From user ID must be positive")
        Long fromUserId,

        @NotNull(message = "To user ID is required")
        @Positive(message = "To user ID must be positive")
        Long toUserId,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
        BigDecimal amount,

        LocalDateTime timestamp
) {}