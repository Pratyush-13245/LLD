package com.bank.app;

import com.bank.model.Transaction;
import com.bank.service.BankService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingCLI {
    private final BankService bankService;
    private final Scanner scanner;

    public BankingCLI(BankService bankService) {
        this.bankService = bankService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {

           printMenu();

            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number from 1 to 7.");
                scanner.next();
                continue;
            }
            try {
                switch (choice) {
                    case 1:
                        createAccount();
                        break;

                    case 2:
                        depositMoney();
                        break;

                    case 3:
                        withdrawMoney();
                        break;

                    case 4:
                        transferMoney();
                        break;

                    case 5:
                        checkBalance();
                        break;

                    case 6:
                        viewTransactionHistory();
                        break;

                    case 7:
                        running = false;
                        System.out.println("Thank you for using the Banking System.");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
            catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
    private  void printMenu() {
        System.out.println("\n===== BANKING SYSTEM =====");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Check Balance");
        System.out.println("6. View Transaction History");
        System.out.println("7. Exit");
    }

    private void createAccount()
    {
        System.out.println("Enter Account Number");
        String
                accountNumber = scanner.next();
        System.out.println("Enter Account holder name");
        String accountHolderName = scanner.next();
        bankService.createAccount(accountNumber, accountHolderName);
        System.out.println("Account created successfully.");
    }
    private  void depositMoney()
    {
        System.out.println("Enter Account Number");
        String depositAccountNumber = scanner.next();
        System.out.println("Please enter amount to Deposit");
        BigDecimal depositAmount = scanner.nextBigDecimal();
        bankService.deposit(depositAccountNumber, depositAmount);
        System.out.println("Account deposited successfully.");
    }
    private void withdrawMoney()
    {
        System.out.println("Enter Account Number");
        String withdrawAccountNumber = scanner.next();
        System.out.println("Please enter amount to withdraw");
        BigDecimal withdrawAmount = scanner.nextBigDecimal();
        bankService.withdraw(withdrawAccountNumber, withdrawAmount);
        System.out.println("Account withdrawn successfully.");
    }

    private void transferMoney()
    {
        System.out.println("Enter Source Account Number");
        String sourceAccountNumber = scanner.next();
        System.out.println("Enter Destination Account Number");
        String destinationAccountNumber = scanner.next();
        System.out.println("Please enter amount to Transfer");
        BigDecimal transferAmount = scanner.nextBigDecimal();
        bankService.transfer(sourceAccountNumber, destinationAccountNumber, transferAmount);
        System.out.println("The amount transferred successfully.");
    }
    private void  checkBalance()
    {
        System.out.println("Enter Account Number");
        String balanceAccountNumber = scanner.next();
        System.out.println("The account Balance is " + bankService.getAccount(balanceAccountNumber).getBalance());
    }

    private void viewTransactionHistory()
    {
        System.out.println("Enter Account Number");
        String transactionAccountNumber = scanner.next();

        System.out.println("===== TRANSACTION HISTORY =====");

        for (Transaction transaction :
                bankService.getAccount(transactionAccountNumber).getTransactions()) {

            System.out.println(transaction);
        }

    }
}
