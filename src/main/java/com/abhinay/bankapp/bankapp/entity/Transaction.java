package com.abhinay.bankapp.bankapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_amount", nullable = false)
    @NotNull(message = "Amount cannot be null")
    private BigDecimal transactionAmount;

    @ManyToOne
    @JoinColumn(name = "sender_account_number", referencedColumnName = "account_number", nullable = false)
    @NotNull(message = "Sender Account cannot be null")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_number", referencedColumnName = "account_number", nullable = false)
    @NotNull(message = "Receiver Account cannot be null")
    private Account receiverAccount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "status")
    private String status;

    //construct
    public Transaction() {
    }

    public Transaction(Long id, BigDecimal transactionAmount, Account senderAccount, Account receiverAccount, LocalDateTime transactionDate, String status) {
        this.id = id;
        this.transactionAmount = transactionAmount;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.transactionDate = transactionDate;
        this.status = status;
    }



    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //to string
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionAmount=" + transactionAmount +
                ", senderAccount=" + senderAccount +
                ", receiverAccount=" + receiverAccount +
                ", transactionDate=" + transactionDate +
                ", status='" + status + '\'' +
                '}';
    }


}
