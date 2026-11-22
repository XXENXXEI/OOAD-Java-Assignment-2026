package com.bank;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;
import com.bank.util.DatabaseSetup;

public class App {
    public static void main(String[] args) {
        DatabaseSetup.createTables();

                CustomerDAO dao = new CustomerDAO();
        Customer found = dao.findByEmail("Billy@gmail.com");
        if (found != null) {
            System.out.println("Found: " + found.getName());
        } else {
            System.out.println("Not found");
        }
    }
}