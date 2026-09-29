
package com.bank.app;
import com.bank.repository.BankAccountRepository;
import com.bank.service.BankService;

public class Main {

    public static void main(String[] args) {

        BankAccountRepository repository = new BankAccountRepository();
        BankService bankService = new BankService(repository);
        BankingCLI bankingCLI = new BankingCLI(bankService);

        bankingCLI.start();
    }
}