package com.bank;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;
import com.bank.util.DatabaseSetup;

public class App {
    public static void main(String[] args) {
        DatabaseSetup.createTables();

        Customer c = new Customer("Billy", "2005-03-14", "Billy@gmail.com",
                                  "71234567", "Gaborone", "password123");
        new CustomerDAO().save(c);
    }
}