CREATE TABLE IF NOT EXISTS customers (
    customer_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    date_of_birth TEXT,
    email TEXT NOT NULL,
    phone_number TEXT,
    address TEXT,
    password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
    account_number TEXT PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    account_type TEXT NOT NULL,
    balance REAL NOT NULL,
    bank_branch TEXT,
    employer_name TEXT,
    employer_account_number TEXT,
    employer_address TEXT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);