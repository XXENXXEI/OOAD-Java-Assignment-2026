package com.bank.dao;

import com.bank.model.Customer;
import com.bank.util.DatabaseConnection;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDAO {

    // saves a new customer into the customers table
    public void save(Customer customer) {
        String sql = "INSERT INTO customers "
                   + "(name, date_of_birth, email, phone_number, address, password) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getDateOfBirth());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getPassword());

            ps.executeUpdate();
            System.out.println("Customer saved: " + customer.getName());

        } catch (Exception e) {
            System.out.println("Could not save customer: " + e.getMessage());
        }
    }

    // looks up a customer by email, Returns null if not found
    public Customer findByEmail(String email) {
        String sql = "SELECT * FROM customers WHERE email = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Customer(
                    rs.getString("name"),
                    rs.getString("date_of_birth"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("address"),
                    rs.getString("password"));
            }

        } catch (Exception e) {
            System.out.println("Could not find customer: " + e.getMessage());
        }
        return null;
    }

        // will remove a customer from the database
    public void delete(String email) {
        String sql = "DELETE FROM customers WHERE email = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.executeUpdate();
            System.out.println("Customer deleted: " + email);

        } catch (Exception e) {
            System.out.println("Could not delete customer: " + e.getMessage());
        }
    }
}