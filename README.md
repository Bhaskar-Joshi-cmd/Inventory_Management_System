# Inventory Management System

A robust desktop-based Inventory Management Application developed using **Core Java**, **JDBC**, and **MySQL**. This project was designed to streamline the tracking of products, categories, customers, and orders, featuring automated PDF bill generation.

## 🚀 Features

* **User Management:** Secure login system with different user roles.
* **Product & Category Tracking:** Complete CRUD (Create, Read, Update, Delete) operations for inventory items.
* **Order Management:** Process customer orders and automatically update stock levels.
* **PDF Generation:** Automated creation of invoices/bills using the iText library.
* **Data Persistence:** Reliable storage using MySQL database with optimized JDBC connections.
* **Dynamic UI:** Built using Java Swing for a responsive desktop experience.

## 🛠️ Technical Stack

* **Language:** Java (JDK 21+)
* **Database:** MySQL 8.0+
* **Libraries:** * JDBC (MySQL Connector/J)
    * iText (for PDF generation)
* **IDE:** NetBeans / IntelliJ IDEA

## 📋 Prerequisites

Before running the application, ensure you have the following installed:
1.  **Java Development Kit (JDK):** Version 21 or higher.
2.  **MySQL Server:** Ensure the service is running.
3.  **MySQL Workbench:** (Optional) For easier database management.

## ⚙️ Setup & Installation

### 1. Database Setup
1.  Open your MySQL terminal or Workbench.
2.  Create a new database:
    ```sql
    CREATE DATABASE inventory_db;
    ```
3.  Import the provided `database_setup.sql` file to create the necessary tables and sample data.

### 2. Configuration
The application uses an external configuration file for database security.
1.  Locate the `db.properties` file in the project root.
2.  Update the credentials to match your local MySQL setup:
    ```properties
    db.url=jdbc:mysql://localhost:3306/inventory_db
    db.user=your_username
    db.password=your_password
    ```

### 3. Running the Application
* **Via IDE:** Open the project folder in NetBeans, right-click the project, and select **Run**.
* **Via JAR:** Navigate to the `dist` folder and run:
    ```bash
    java -jar InventoryManagementSystem.jar
    ```

## 📂 Project Structure

* `src/com/inventory/ui`: Contains Swing GUI forms and panels.
* `src/com/inventory/dao`: Data Access Objects for database operations.
* `src/com/inventory/model`: Plain Old Java Objects (POJOs) representing entities.
* `src/com/inventory/util`: Database connection utilities.
* `lib/`: External JAR dependencies.

