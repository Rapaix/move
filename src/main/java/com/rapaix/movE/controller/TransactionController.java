package com.rapaix.movE.controller;

import com.rapaix.movE.dto.TransactionDTO;
import com.rapaix.movE.entity.Transaction;
import com.rapaix.movE.messaging.TransactionProducer;
import com.rapaix.movE.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction", description = "Transaction management APIs")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionProducer transactionProducer;

    @Autowired
    public TransactionController(TransactionService transactionService, TransactionProducer transactionProducer) {
        this.transactionService = transactionService;
        this.transactionProducer = transactionProducer;
    }

    @PostMapping
    @Operation(summary = "Create a new transaction", description = "Creates a new transaction between users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Transaction accepted"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<TransactionDTO> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        transactionProducer.sendTransaction(transactionDTO);
        return new ResponseEntity<>(transactionDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping
    @Operation(summary = "Get all transactions", description = "Retrieves a list of all transactions")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionDTOs);
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getFromUser().getId(),
                transaction.getToUser().getId(),
                transaction.getAmount(),
                transaction.getTimestamp()
        );
    }
}
