
package com.bank.service;

import com.bank.model.BankAccount;
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
    }
}