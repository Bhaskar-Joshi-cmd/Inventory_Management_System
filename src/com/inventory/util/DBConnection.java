/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.util;

/**
 *
 * @author bhaskarjoshi
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.util.Properties;

public class DBConnection {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Bhaskar123"; 

    public static Connection getConnection() {
        Properties props = new Properties();
    // This looks for the file in the folder where the app started
    try (FileInputStream in = new FileInputStream("db.properties")) {
        props.load(in);
        return DriverManager.getConnection(
            props.getProperty("db.url"), 
            props.getProperty("db.user"), 
            props.getProperty("db.password")
        );
    } catch (Exception e) {
        // If file is missing, show an error instead of crashing silently
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Database Config Error: Please ensure db.properties exists.\n" + e.getMessage());
        return null;
    }
    }

    /* A small test method to verify everything is working in NetBeans
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("SUCCESS: Your Mac is talking to MySQL!");
        } else {
            System.out.println("FAILURE: Check the error messages above.");
        }
    }*/
}
