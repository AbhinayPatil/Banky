package com.abhinay.bankapp.bankapp.repository;

import com.abhinay.bankapp.bankapp.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.senderAccount.accountNumber = :accountNumber")
    Page<Transaction> findBySenderAccount(@Param("accountNumber") String accountNumber, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE t.receiverAccount.accountNumber = :accountNumber")
    Page<Transaction> findByReceiverAccount(@Param("accountNumber") String accountNumber, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE t.senderAccount.accountNumber = :accountNumber OR t.receiverAccount.accountNumber = :accountNumber")
    Page<Transaction> findBySenderAccountOrReceiverAccount(@Param("accountNumber") String accountNumber, Pageable pageable);
}
