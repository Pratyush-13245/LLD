
package com.bank.service;

import com.bank.enums.TransactionType;
import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import com.bank.repository.BankAccountRepository;

import java.math.BigDecimal;

public class BankService {

    private final BankAccountRepository bankAccountRepository;

    public BankService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createAccount(
            String accountHolderName,
            String accountNumber
    ) {

        if (accountHolderName == null || accountNumber == null) {
            throw new IllegalArgumentException(
                    "Arguments cannot be null!"
            );
        }

        if (accountHolderName.isBlank() || accountNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "Arguments cannot be blank!"
            );
        }

        BankAccount bankAccount =
                new BankAccount(accountNumber, accountHolderName);

        bankAccountRepository.addAccount(bankAccount);

        return bankAccount;
    }

    public BankAccount getAccount(String accountNumber) {
        if (accountNumber == null) {
            throw new IllegalArgumentException("Arguments cannot be null!");
        }
        if(accountNumber.isBlank()) {
            throw new IllegalArgumentException("Arguments cannot be blank!");
        }
        BankAccount account = bankAccountRepository.getAccount(accountNumber);
        return account;
    }

    public void  deposit(String accountNumber, BigDecimal amount) {
        if (accountNumber == null) {
            throw new IllegalArgumentException("Arguments cannot be null!");
        }
        if(accountNumber.isBlank()) {
            throw new IllegalArgumentException("Arguments cannot be blank!");
        }
        BankAccount bankAccount = bankAccountRepository.getAccount(accountNumber);
        bankAccount.deposit(amount);
        Transaction transaction = new Transaction(
                java.util.UUID.randomUUID().toString(),
                TransactionType.DEPOSIT,
                amount,
                accountNumber
        );

        bankAccount.addTransaction(transaction);
    }

    public void  withdraw(String accountNumber, BigDecimal amount) {
        if (accountNumber == null) {
            throw new IllegalArgumentException("Arguments cannot be null!");
        }
        if(accountNumber.isBlank()) {
            throw new IllegalArgumentException("Arguments cannot be blank!");
        }
        BankAccount bankAccount = bankAccountRepository.getAccount(accountNumber);
        bankAccount.withdraw(amount);

        Transaction transaction = new Transaction(
                java.util.UUID.randomUUID().toString(),
                TransactionType.WITHDRAWL,
                amount,
                accountNumber
        );

        bankAccount.addTransaction(transaction);

    }

    public void transfer(
            String fromAccountNumber,
            String toAccountNumber,
            BigDecimal amount
    ) {
        if (fromAccountNumber == null || toAccountNumber == null) {
            throw new IllegalArgumentException(
                    "Account numbers cannot be null!"
            );
        }

        if (fromAccountNumber.isBlank() || toAccountNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "Account numbers cannot be blank!"
            );
        }

        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new IllegalArgumentException(
                    "Accounts cannot be the same!"
            );
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Transfer amount must be greater than zero!"
            );
        }

        BankAccount fromAccount =
                bankAccountRepository.getAccount(fromAccountNumber);

        BankAccount toAccount =
                bankAccountRepository.getAccount(toAccountNumber);

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds!");
        }

        // Perform balance updates directly.
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        // Record exactly two transfer entries.
        Transaction fromTransaction = new Transaction(
                java.util.UUID.randomUUID().toString(),
                TransactionType.TRANSFER,
                amount,
                fromAccountNumber
        );

        Transaction toTransaction = new Transaction(
                java.util.UUID.randomUUID().toString(),
                TransactionType.TRANSFER,
                amount,
                toAccountNumber
        );

        fromAccount.addTransaction(fromTransaction);
        toAccount.addTransaction(toTransaction);
    }

}