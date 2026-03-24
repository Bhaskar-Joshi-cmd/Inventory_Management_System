📦 Smart Inventory Management System (Pro Edition)
An advanced, desktop-based Inventory Management Application developed using Core Java (JDK 21), JDBC, and MySQL. This system is engineered to bridge the gap between manual record-keeping and complex ERPs by providing intelligent monitoring, hardware integration, and data-driven insights.

🚀 Key Features
Advanced Search & Barcode Integration: High-speed product lookup using manual search or USB Barcode Scanners, featuring automated UI focus for rapid checkouts.

Intelligent Audit Logs: A comprehensive System Audit Trail that tracks every internal stock movement (IN/OUT) with timestamps and user accountability.

Business Analytics & Data Visualization: Dedicated Sales Analysis Dashboard providing insights into total revenue, average order value, and best-selling products.

Automated PDF Billing: Instant generation of professional invoices using the iText library, with integrated PDF viewing capabilities.

Smart Alert System: Real-time monitoring for Low-Stock thresholds and Product Expiry tracking to prevent wastage.

Role-Based Security: Secure login system with access control for Admins and Staff.

Relational Data Management: Optimized MySQL schema with indexed lookups for high performance.

🛠️ Technical Stack
Language: Java (JDK 21+)

GUI Framework: Java Swing (Customized with STSong typography)

Database: MySQL 8.0+

Libraries:

mysql-connector-j-9.1.0.jar (Database Connectivity)

itextpdf-5.5.13.jar (Document Generation)

jcalendar-1.4.jar (Date Management)

IDE: NetBeans 23+

📋 Prerequisites
Before running the application, ensure you have:

Java Development Kit (JDK): Version 21 or higher.

MySQL Server: Running on port 3306.

Barcode Scanner: (Optional) Any standard USB HID Barcode Scanner.

⚙️ Setup & Installation
1. Database Initialization
Open your MySQL terminal or Workbench.

Create the database and backfill historical logs:

SQL
CREATE DATABASE inventory_db;
Run the database_setup.sql script located in the root folder to initialize tables and relationships.

2. Dependency Configuration
Ensure all .jar files in the /lib folder are added to your project's Libraries/Classpath in your IDE (NetBeans/IntelliJ).

3. Database Connection
Update the DBConnection.java or db.properties with your local credentials:

Properties
db.url=jdbc:mysql://localhost:3306/inventory_db
db.user=your_username
db.password=your_password
📂 Project Architecture
src/com/inventory/ui: Modular Swing Panels (Manage Orders, Sales Analysis, Audit Logs).

src/com/inventory/dao: Data Access Objects utilizing the DAO Design Pattern.

src/com/inventory/model: Encapsulated POJOs (Product, Order, Transaction).

src/com/inventory/util: Singleton Database connection and Image utility classes.

generated_pdfs/: Default storage directory for all generated invoices.
