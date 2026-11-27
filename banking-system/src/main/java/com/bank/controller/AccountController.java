package com.bank.controller;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;
import com.bank.model.ChequeAccount;
import com.bank.model.Customer;
import com.bank.model.InvestmentAccount;
import com.bank.model.SavingsAccount;

public class AccountController {

    private AccountDAO accountDAO = new AccountDAO();

    // will open a new account for a customer and save it
    public Account openAccount(Customer customer, String type, String accountNumber,
                               String branch, double openingDeposit,
                               String employerName, String employerAccountNumber,
                               String employerAddress) {

        if (customer == null) {
            throw new IllegalArgumentException("Log in first");
        }
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required");
        }
        if (openingDeposit < 0) {
            throw new IllegalArgumentException("Opening deposit cannot be negative");
        }

        Account account;
        if (type.equals("SAVINGS")) {
            account = new SavingsAccount(accountNumber, branch, customer, openingDeposit);
        } else if (type.equals("INVESTMENT")) {
            account = new InvestmentAccount(accountNumber, branch, customer, openingDeposit);
        } else if (type.equals("CHEQUE")) {
            account = new ChequeAccount(accountNumber, branch, customer, openingDeposit,
                    employerName, employerAccountNumber, employerAddress);
        } else {
            throw new IllegalArgumentException("Unknown account type");
        }

        accountDAO.save(account);
        return account;
    }
}