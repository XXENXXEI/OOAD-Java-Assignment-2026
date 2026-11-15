package com.bank.model;

public abstract class Account {

    private double balance;
    private String accountNumber;
    private String bankBranch;
    private Customer owner;

    // Constructor: an account must have an owner
    public Account(String accountNumber, String bankBranch,
                   Customer owner, double openingDeposit) {
        if (owner == null) {
            throw new IllegalArgumentException("An account must have a customer");
        }
        this.accountNumber = accountNumber;
        this.bankBranch = bankBranch;
        this.owner = owner;
        this.balance = openingDeposit;
        owner.addAccount(this);   // adds this account to the customer's list
    }

    // Deposit money (same for all account types)
    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
        }
    }

    // Overloading: same method name, different parameters
    public void deposit(double amount, String description) {
        deposit(amount);
        System.out.println("Deposit: " + description);
    }

    // Abstract: every child class must write its own version
    public abstract boolean withdraw(double amount);

    public double getBalance() { return balance; }

    // Child classes use this to change the balance
    protected void setBalance(double newBalance) {
        balance = newBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getBankBranch() { return bankBranch; }
    public Customer getOwner() { return owner; }
}