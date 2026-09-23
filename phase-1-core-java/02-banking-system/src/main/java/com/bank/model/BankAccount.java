package com.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bank.model.Transaction;
import com.bank.enums.TransactionType;

public class BankAccount {
    private final String accountNumber;
    private final String accountHolderName;
    private BigDecimal balance;
    private final List<Transaction> transactions = new ArrayList<>();
    public BankAccount(String accountNumber, String accountHolderName) {
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
        if(balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Insufficient balance!");
        }
        balance = balance.subtract(amount);
    }
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
