
package com.bank.app;

import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import com.bank.repository.BankAccountRepository;
import com.bank.service.BankService;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // 1. Initialize repository and service
        BankAccountRepository repository =
                new BankAccountRepository();

        BankService bankService =
                new BankService(repository);

        // 2. Create accounts
        BankAccount account1 =
                bankService.createAccount("Prat", "1001");

        BankAccount account2 =
                bankService.createAccount("Praty", "1002");

        System.out.println("=== Accounts Created ===");

        // 3. Deposit money into account 1001
        bankService.deposit(
                "1001",
                new BigDecimal("500.00")
        );

        // 4. Transfer money from 1001 to 1002
        bankService.transfer(
                "1001",
                "1002",
                new BigDecimal("200.00")
        );

        // 5. Print final account details
        System.out.println("\n=== Final Account Details ===");

        printAccountDetails(bankService, "1001");
        printAccountDetails(bankService, "1002");

        // 6. Print transaction history
        System.out.println("\n=== Transaction History ===");

        printTransactionHistory(account1);
        printTransactionHistory(account2);
    }

    private static void printAccountDetails(
            BankService bankService,
            String accountNumber
    ) {
        BankAccount account =
                bankService.getAccount(accountNumber);

        System.out.println(
                "\nAccount Number: " + account.getAccountNumber()
        );

        System.out.println(
                "Account Holder: " + account.getAccountHolderName()
        );

        System.out.println(
                "Balance: " + account.getBalance()
        );
    }

    private static void printTransactionHistory(
            BankAccount account
    ) {
        System.out.println(
                "\nTransactions for " +
                        account.getAccountHolderName() +
                        " (" + account.getAccountNumber() + "):"
        );

        for (Transaction transaction : account.getTransactions()) {
            System.out.println(
                    transaction.getTransactionType() +
                            " | Amount: " + transaction.getAmount() +
                            " | Time: " + transaction.getTimestamp()
            );
        }
    }
}