/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

/**
 *
 * @author bhaskarjoshi
 */

import com.inventory.model.Transaction;
import com.inventory.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    /**
     * Records a new transaction in the history log.
     * This happens whenever stock is added or removed.
     */
    public boolean logTransaction(Transaction trans) {
        String query = "INSERT INTO transactions (user_id, product_id, transaction_type, quantity_changed) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, trans.getUserId());
            pstmt.setInt(2, trans.getProductId());
            pstmt.setString(3, trans.getTransactionType());
            pstmt.setInt(4, trans.getQuantityChanged());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error logging transaction:");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the full history of all transactions.
     * This will be used for your "History" or "Reports" screen.
     */
    public List<Transaction> getAllTransactions() {
        List<com.inventory.model.Transaction> list = new ArrayList<>();
    // SQL JOIN to get the Product Name
    String query = "SELECT t.*, p.name FROM transactions t " +
                   "JOIN products p ON t.product_id = p.product_id " +
                   "ORDER BY t.trans_date DESC";

    try (java.sql.Connection conn = com.inventory.util.DBConnection.getConnection();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(query);
         java.sql.ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            com.inventory.model.Transaction trans = new com.inventory.model.Transaction();
            trans.setTransId(rs.getInt("trans_id"));
            trans.setUserId(rs.getInt("user_id"));
            trans.setProductId(rs.getInt("product_id")); // You can use a String for name if preferred
            trans.setTransactionType(rs.getString("transaction_type"));
            trans.setQuantityChanged(rs.getInt("quantity_changed"));
            trans.setTransDate(rs.getTimestamp("trans_date"));
            list.add(trans);
        }
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
    }
    return list;
    }
    
    //Testing
    
    
    public static void main(String[] args) {
    TransactionDAO dao = new TransactionDAO();
    
    // Create a test transaction object
    // Assuming User 1 (Bhaskar) and Product 1 (MacBook) exist from our previous SQL script
    com.inventory.model.Transaction testTrans = new com.inventory.model.Transaction();
    testTrans.setUserId(1); 
    testTrans.setProductId(1);
    testTrans.setTransactionType("IN");
    testTrans.setQuantityChanged(5);

    if (dao.logTransaction(testTrans)) {
        System.out.println("TEST PASSED: Transaction logged successfully!");
        
        // Now check if we can read it back
        java.util.List<com.inventory.model.Transaction> history = dao.getAllTransactions();
        System.out.println("Total Transactions in DB: " + history.size());
    } else {
        System.out.println("TEST FAILED: Check your Foreign Key constraints!");
    }
}
}
