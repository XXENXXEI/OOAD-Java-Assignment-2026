package com.bank.dao;

import com.bank.model.Account;
import com.bank.model.ChequeAccount;
import com.bank.model.InvestmentAccount;
import com.bank.model.SavingsAccount;
import com.bank.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDAO {

    // Saves a new account into the accounts table
    public void save(Account account) {
        String type;
        String employerName = null;
        String employerAccountNumber = null;
        String employerAddress = null;

        // Work out which kind of account this is
        if (account instanceof SavingsAccount) {
            type = "SAVINGS";
        } else if (account instanceof InvestmentAccount) {
            type = "INVESTMENT";
        } else {
            type = "CHEQUE";
            ChequeAccount cheque = (ChequeAccount) account;
            employerName = cheque.getEmployerName();
            employerAccountNumber = cheque.getEmployerAccountNumber();
            employerAddress = cheque.getEmployerAddress();
        }

        // The (SELECT ...) finds the owner's id using their email
        String sql = "INSERT INTO accounts "
                   + "(account_number, customer_id, account_type, balance, bank_branch, "
                   + "employer_name, employer_account_number, employer_address) "
                   + "VALUES (?, (SELECT customer_id FROM customers WHERE email = ?), "
                   + "?, ?, ?, ?, ?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, account.getAccountNumber());
            ps.setString(2, account.getOwner().getEmail());
            ps.setString(3, type);
            ps.setDouble(4, account.getBalance());
            ps.setString(5, account.getBankBranch());
            ps.setString(6, employerName);
            ps.setString(7, employerAccountNumber);
            ps.setString(8, employerAddress);

            ps.executeUpdate();
            System.out.println("Account saved: " + account.getAccountNumber());

        } catch (Exception e) {
            System.out.println("Could not save account: " + e.getMessage());
        }
    }
}