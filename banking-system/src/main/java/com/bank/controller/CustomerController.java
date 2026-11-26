package com.bank.controller;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;

public class CustomerController {

    private CustomerDAO customerDAO = new CustomerDAO();

    // will chech the input, then creates and saves a customer
    // if something is wrong, will give an error with a message on the screen
    public Customer register(String name, String dateOfBirth, String email,
                             String phone, String address, String password) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Enter a valid email");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (customerDAO.findByEmail(email) != null) {
            throw new IllegalArgumentException("That email is already registered");
        }

        Customer customer = new Customer(name, dateOfBirth, email, phone, address, password);
        customerDAO.save(customer);
        return customer;
    }

        // will check the email and password and return the customer if correct
    public Customer login(String email, String password) {
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Enter your email and password");
        }

        Customer customer = customerDAO.findByEmail(email);

        if (customer == null || !customer.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong email or password");
        }
        return customer;
    }
}