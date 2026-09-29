package com.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bank.exception.InsufficientBalanceException;

public class BankAccount {
    private final String accountNumber;
    private final String accountHolderName;
    private BigDecimal balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String accountNumber, String accountHolderName) {
        if (accountNumber == null || accountNumber.isBlank() || accountHolderName == null || accountHolderName.isBlank()) {
            throw new IllegalArgumentException("Account number or account holder name is null or empty");
        }
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = BigDecimal.ZERO;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero!"
            );
        }

        balance = balance.add(amount);

    }

    public void withdraw(BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero!");
        }
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance!");
        }
        balance = balance.subtract(amount);
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null!");
        }

        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
