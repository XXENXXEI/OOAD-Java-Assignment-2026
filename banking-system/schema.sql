CREATE TABLE IF NOT EXISTS customers (
    customer_id INTEGER PRIMARY KEY,
    customer_name TEXT,
    date_of_birth TEXT,
    email TEXT,
    phone_number TEXT,
    customer_address TEXT,
    customer_password TEXT
);

CREATE TABLE IF NOT EXISTS accounts (
    account_number INTEGER PRIMARY KEY,
    customer_id INTEGER,
    account_type TEXT,
    balance REAL,
    bank_branch TEXT,
    employer_name TEXT,
    employer_account_number TEXT,
    employer_address TEXT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);