-- Drop Tables (in reverse order to maintain integrity)
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

-- Create Users Table
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(15),
    role ENUM('ADMIN', 'USER') DEFAULT 'USER' NOT NULL
);

-- Create Categories Table
CREATE TABLE categories (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE
);

-- Create Products Table
CREATE TABLE products (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    price DOUBLE NOT NULL CHECK (price > 0),
    stock_quantity INT NOT NULL CHECK (stock_quantity >= 0),
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE
);

-- Create Orders Table
CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_date DATE NOT NULL,
    total_amount DOUBLE NOT NULL CHECK (total_amount > 0),
    status ENUM('Pending', 'Completed', 'Cancelled') NOT NULL DEFAULT 'Pending',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Create Order Details Table
CREATE TABLE order_details (
    order_detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price_per_unit DOUBLE NOT NULL CHECK (price_per_unit > 0),
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);

-- Create Payments Table
CREATE TABLE payments (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    payment_method ENUM('CARD', 'UPI', 'PAYPAL') NOT NULL,
    payment_status ENUM('PENDING', 'SUCCESS', 'FAILED') DEFAULT 'PENDING',
    transaction_id VARCHAR(100) UNIQUE,
    amount DOUBLE NOT NULL CHECK (amount > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);

-- Insert Sample Data into Users
INSERT INTO users (name, email, password, address, phone_number, role) VALUES
('Admin User', 'admin@example.com', 'admin123', '123 Admin St', '1234567890', 'ADMIN'),
('John Doe', 'john@example.com', 'john123', '456 Main St', '9876543210', 'USER'),
('Jane Smith', 'jane@example.com', 'jane123', '789 Elm St', '5551234567', 'USER');

-- Insert Sample Data into Categories
INSERT INTO categories (category_name) VALUES
('Electronics'),
('Clothing'),
('Books');

-- Insert Sample Data into Products
INSERT INTO products (name, description, price, stock_quantity, category_id) VALUES
('Smartphone', 'Latest model with advanced features', 699.99, 50, 1),
('Laptop', 'High-performance laptop for professionals', 1299.99, 30, 1),
('T-shirt', 'Comfortable cotton T-shirt', 19.99, 100, 2),
('Fiction Book', 'Bestselling novel', 14.99, 200, 3);

-- Insert Sample Data into Orders
INSERT INTO orders (user_id, order_date, total_amount, status) VALUES
(2, '2023-10-01', 719.98, 'Pending'),
(3, '2023-10-02', 1339.98, 'Completed');

-- Insert Sample Data into Order Details
INSERT INTO order_details (order_id, product_id, quantity, price_per_unit) VALUES
(1, 1, 1, 699.99),
(1, 3, 1, 19.99),
(2, 2, 1, 1299.99),
(2, 4, 1, 14.99);

-- Insert Sample Data into Payments
INSERT INTO payments (order_id, payment_method, payment_status, transaction_id, amount)
VALUES 
    (1, 'CARD', 'SUCCESS', 'TXN1234567890', 719.98),
    (2, 'UPI', 'PENDING', 'TXN1234567891', 1339.98);