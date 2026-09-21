
package com.bank.repository;

import com.bank.model.BankAccount;

import java.util.HashMap;
import java.util.Map;

public class BankAccountRepository {

    private final Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null!");
        }

        String accountNumber = account.getAccountNumber();

        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException(
                    "Account number already exists!"
            );
        }

        accounts.put(accountNumber, account);
    }

    public BankAccount getAccount(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found!");
        }

        return account;
    }
}