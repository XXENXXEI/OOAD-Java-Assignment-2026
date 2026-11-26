package com.bank;

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

         CustomerController controller = new CustomerController();
        try {
            Customer c = controller.login("bigboss@mail.com", "pass123");
            System.out.println("Welcome, " + c.getName());
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}