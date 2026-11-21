package com.bank.model;

public class InvestmentAccount extends Account implements InterestCalc {

    private double interestRate = 0.05;
    private double minimumOpeningDeposit = 500.00;

    public InvestmentAccount(String accountNumber, String bankBranch,
                             Customer owner, double openingDeposit) {
        super(accountNumber, bankBranch, owner, checkDeposit(openingDeposit));
    }

    // refuses deposits under P500
    private static double checkDeposit(double openingDeposit) {
        if (openingDeposit < 500.00) {
            throw new IllegalArgumentException(
                "Investment account needs at least P500 to open");
        }
        return openingDeposit;
    }

    // investment accounts allow withdrawals, but not more than the balance
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    public void payInterest() {
        deposit(calculateInterest());
    }

    public double getInterestRate() { return interestRate; }
    public double getMinimumOpeningDeposit() { return minimumOpeningDeposit; }
}