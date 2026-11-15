package com.bank.model;

public class SavingsAccount extends Account implements InterestCalc {

    // 0.05% monthly = 0.0005 as a decimal
    private double interestRate = 0.0005;

    public SavingsAccount(String accountNumber, String bankBranch,
                          Customer owner, double openingDeposit) {
        super(accountNumber, bankBranch, owner, openingDeposit);
    }

    // Savings accounts do not allow withdrawals
    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    // Interest = balance x rate
    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    public double getInterestRate() { return interestRate; }
}