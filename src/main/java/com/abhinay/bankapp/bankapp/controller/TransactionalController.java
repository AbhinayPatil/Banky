package com.abhinay.bankapp.bankapp.controller;

import com.abhinay.bankapp.bankapp.dto.GetTransactionsDto;
import com.abhinay.bankapp.bankapp.dto.PaginatedDto;
import com.abhinay.bankapp.bankapp.entity.Transaction;
import com.abhinay.bankapp.bankapp.service.TransactionService;
import com.abhinay.bankapp.bankapp.util.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
@PreAuthorize("hasAuthority('User')")
public class TransactionalController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping
    public ResponseEntity<ApplicationResponse> getTransactions(@RequestBody GetTransactionsDto getTransactionsDto) {
        ApplicationResponse applicationResponse = new ApplicationResponse();
        try {
            PaginatedDto users = transactionService.getTransactions(getTransactionsDto);
            applicationResponse.setStatus("SUCCESS");
            applicationResponse.setMessage("Transactions fetched successfully");
            applicationResponse.setData(users);
        } catch (Exception e) {
            System.out.println("Error fetching Transaction: " + e.getMessage());
            applicationResponse.setStatus("FAIL");
            applicationResponse.setMessage("Error fetching Transaction: " + e.getMessage());
        }
        return ResponseEntity.ok(applicationResponse);
    }
}
