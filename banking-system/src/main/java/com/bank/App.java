package com.bank;

import com.bank.model.SavingsAccount;
import com.bank.model.Account;
import java.util.List;
import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;
import com.bank.util.DatabaseSetup;
import com.bank.dao.AccountDAO;
import com.bank.model.SavingsAccount;

public class App {
    public static void main(String[] args) {
        DatabaseSetup.createTables();

        Customer billy = new CustomerDAO().findByEmail("Billy@gmail.com");
        SavingsAccount temp = new SavingsAccount("TEMP1", "Main", billy, 100);
        AccountDAO dao = new AccountDAO();
        dao.save(temp);
        dao.delete("TEMP1");
    }
}