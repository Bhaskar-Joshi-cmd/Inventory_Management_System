-- 1. Create and Select the Database
CREATE DATABASE IF NOT EXISTS inventory_db;
USE inventory_db;

-- 2. Created CATEGORIES Table
CREATE TABLE IF NOT EXISTS categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL UNIQUE
);

-- 3. Created USERS Table
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- In real life, hash this!
    role VARCHAR(20) NOT NULL CHECK (role IN ('admin', 'employee'))
);

-- 4. Created PRODUCTS Table
CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    current_quantity INT NOT NULL DEFAULT 0,
    min_threshold INT NOT NULL DEFAULT 5, -- Triggers Low Stock Alert
    expiry_date DATE,                     -- Triggers Expiry Alert
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET NULL
);

-- 5. Created TRANSACTIONS Table
CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    user_id INT,
    transaction_type VARCHAR(20) NOT NULL CHECK (transaction_type IN ('SALE', 'RESTOCK', 'ADJUSTMENT')),
    quantity_changed INT NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Dummy Data
-- Add Categories
INSERT INTO categories (category_name) VALUES ('Electronics'), ('Groceries'), ('Pharma');

-- Add Users (Admin and Employee)
INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'admin'),
('john', 'pass123', 'employee');

-- Add Products
INSERT INTO products (name, price, current_quantity, min_threshold, expiry_date, category_id) VALUES
('MacBook Air', 95000.00, 10, 2, NULL, 1),
('Paracetamol', 50.00, 100, 20, '2026-12-31', 3),
('Fresh Milk', 60.00, 50, 10, '2026-02-20', 2);