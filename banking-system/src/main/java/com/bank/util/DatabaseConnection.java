package com.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // The database is a file called bank.db
    private static final String URL = "jdbc:sqlite:bank.db";

    // Every DAO will call this to get a connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}