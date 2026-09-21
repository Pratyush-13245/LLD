
package com.bank.app;

import com.bank.model.BankAccount;
import com.bank.repository.BankAccountRepository;
import com.bank.service.BankService;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        BankAccountRepository repository =
                new BankAccountRepository();

        BankService bankService =
                new BankService(repository);

        BankAccount account =
                bankService.createAccount("Prat", "1001");

        BankAccount bankAccount =
                bankService.createAccount("Praty", "1002");

        System.out.println("Account created successfully!");

        System.out.println(
                "Account Number: " + account.getAccountNumber()
        );

        System.out.println(
                "Account Holder: " + account.getAccountHolderName()
        );

        System.out.println(
                "Balance: " + account.getBalance()
        );

        bankService.deposit("1002", BigDecimal.valueOf(500.00));
        System.out.println();

        System.out.println("Second account details:");

        System.out.println(
                "Account Number: " + bankAccount.getAccountNumber()
        );

        System.out.println(
                "Account Holder: " + bankAccount.getAccountHolderName()
        );

        System.out.println(
                "Balance: " + bankService.getAccount("1002").getBalance()
        );

        bankService.withdraw("1002", BigDecimal.valueOf(200.00));
        System.out.println(
                "Balance: " + bankService.getAccount("1002").getBalance()
        );
    }
}