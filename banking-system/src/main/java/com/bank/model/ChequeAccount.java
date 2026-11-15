package com.bank.model;

public class ChequeAccount extends Account {

    private String employerName;
    private String employerAccountNumber;
    private String employerAddress;

    public ChequeAccount(String accountNumber, String bankBranch,
                         Customer owner, double openingDeposit,
                         String employerName, String employerAccountNumber,
                         String employerAddress) {
        super(accountNumber, bankBranch, owner,
              checkEmployer(employerName, openingDeposit));
        this.employerName = employerName;
        this.employerAccountNumber = employerAccountNumber;
        this.employerAddress = employerAddress;
    }

    // Runs before the account is created: refuses if no employer name
    private static double checkEmployer(String employerName, double openingDeposit) {
        if (employerName == null || employerName.isBlank()) {
            throw new IllegalArgumentException(
                "Cheque account needs an employer (customer must be working)");
        }
        return openingDeposit;
    }

    // Cheque accounts allow withdrawals, but not more than the balance
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    public String getEmployerName() { return employerName; }
    public String getEmployerAccountNumber() { return employerAccountNumber; }
    public String getEmployerAddress() { return employerAddress; }
}