package com.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    // Data about the customer (private = hidden from other classes)
    private String name;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;

    // A customer can have many accounts
    private List<Account> accounts = new ArrayList<>();

    // Constructor: runs when you create a new Customer
    public Customer(String name, String dateOfBirth, String email,
                    String phoneNumber, String address, String password) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    // Give the customer a new account
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    // Getters: let other classes read the private data
    public String getName() { return name; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getPassword() { return password; }
}