package com.bank.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bank.model.Account;
import com.bank.model.Customer;
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

        // Writes the account's current balance back to the database
    public void updateBalance(Account account) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, account.getBalance());
            ps.setString(2, account.getAccountNumber());

            ps.executeUpdate();
            System.out.println("Balance updated: " + account.getAccountNumber());

        } catch (Exception e) {
            System.out.println("Could not update balance: " + e.getMessage());
        }
    }

        // loads accounts that belong to this specific customer
    public List<Account> findByCustomer(Customer customer) {
        List<Account> accounts = new ArrayList<>();

        String sql = "SELECT * FROM accounts WHERE customer_id = "
                   + "(SELECT customer_id FROM customers WHERE email = ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, customer.getEmail());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String number = rs.getString("account_number");
                String branch = rs.getString("bank_branch");
                double balance = rs.getDouble("balance");
                String type = rs.getString("account_type");

                if (type.equals("SAVINGS")) {
                    accounts.add(new SavingsAccount(number, branch, customer, balance));
                } else if (type.equals("INVESTMENT")) {
                    accounts.add(new InvestmentAccount(number, branch, customer, balance));
                } else {
                    accounts.add(new ChequeAccount(number, branch, customer, balance,
                            rs.getString("employer_name"),
                            rs.getString("employer_account_number"),
                            rs.getString("employer_address")));
                }
            }

        } catch (Exception e) {
            System.out.println("Could not load accounts: " + e.getMessage());
        }
        return accounts;
    }

        // removes an account from the database
    public void delete(String accountNumber) {
        String sql = "DELETE FROM accounts WHERE account_number = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, accountNumber);
            ps.executeUpdate();
            System.out.println("Account deleted: " + accountNumber);

        } catch (Exception e) {
            System.out.println("Could not delete account: " + e.getMessage());
        }
    }
}