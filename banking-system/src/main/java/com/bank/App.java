package com.bank;

import com.bank.controller.AccountController;
import com.bank.controller.CustomerController;
import com.bank.model.SavingsAccount;
import com.bank.model.Account;
import java.util.List;
import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;
import com.bank.util.DatabaseSetup;
import com.bank.dao.AccountDAO;
import com.bank.model.SavingsAccount;

public class App {
    public static void main(String[] args) {
        DatabaseSetup.createTables();

        Customer thato = new CustomerDAO().findByEmail("thato@mail.com");
        AccountController controller = new AccountController();
        controller.deposit(thato, "I001", 400);
        controller.payInterest(thato);
    }
}