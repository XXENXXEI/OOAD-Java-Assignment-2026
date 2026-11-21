package com.bank;

import com.bank.util.DatabaseSetup;

public class App {
    public static void main(String[] args) {
        DatabaseSetup.createTables();
    }
}