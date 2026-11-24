package com.bank;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;
import com.bank.util.DatabaseSetup;
import com.bank.dao.AccountDAO;
import com.bank.model.SavingsAccount;

public class App {
    public static void main(String[] args) {
        DatabaseSetup.createTables();

        Customer billy = new CustomerDAO().findByEmail("Billy@gmail.com");
        SavingsAccount savings = new SavingsAccount("S001", "Main", billy, 1000);
        savings.deposit(500);
        new AccountDAO().updateBalance(savings);
    }
}