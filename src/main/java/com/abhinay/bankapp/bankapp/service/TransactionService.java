package com.abhinay.bankapp.bankapp.service;

import com.abhinay.bankapp.bankapp.dto.GetTransactionsDto;
import com.abhinay.bankapp.bankapp.dto.PaginatedDto;
import com.abhinay.bankapp.bankapp.entity.Transaction;
import com.abhinay.bankapp.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public PaginatedDto getTransactions(GetTransactionsDto getTransactionsDto) {
        Pageable pageable = PageRequest.of(getTransactionsDto.getPage(), getTransactionsDto.getSize());
        Page<Transaction> transactionPage;

        switch (getTransactionsDto.getFilter()) {
            case "sent":
                transactionPage = transactionRepository.findBySenderAccount(getTransactionsDto.getAccountNumber(), pageable);
                break;
            case "received":
                transactionPage = transactionRepository.findByReceiverAccount(getTransactionsDto.getAccountNumber(), pageable);
                break;
            case "all":
            default:
                transactionPage = transactionRepository.findBySenderAccountOrReceiverAccount(getTransactionsDto.getAccountNumber(), pageable);
                break;
        }

        return new PaginatedDto(
                transactionPage.getContent(),
                transactionPage.getTotalPages(),
                transactionPage.getTotalElements(),
                transactionPage.getNumber()
        );
    }
}
