package com.bank.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    // Reads schema.sql and runs each command
    public static void createTables() {
        try (Connection c = DatabaseConnection.getConnection();
             Statement s = c.createStatement()) {

            String sql = Files.readString(Path.of("schema.sql"));
            for (String command : sql.split(";")) {
                if (!command.isBlank()) {
                    s.execute(command);
                }
            }
            System.out.println("Tables are ready.");

        } catch (Exception e) {
            System.out.println("Setup failed: " + e.getMessage());
        }
    }
}