package com.bank.model;

import com.bank.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final String transactionId;
    private final TransactionType transactionType;
    private final BigDecimal amount;
    private final String accountNumber;
    private final LocalDateTime timestamp;

    public Transaction(String transactionId, TransactionType transactionType, BigDecimal amount, String accountNumber) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
