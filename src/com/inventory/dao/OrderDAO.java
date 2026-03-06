/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

import javax.swing.table.DefaultTableModel;
import com.inventory.model.Order;
import com.inventory.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author bhaskarjoshi
 */
public class OrderDAO {
    public List<Order> getOrdersByCustomer(int customerId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE customer_id = ? ORDER BY order_date DESC";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public int saveOrder(Order order, DefaultTableModel cartModel) {
    int generatedOrderId = -1;
    String orderQuery = "INSERT INTO orders (customer_id, order_date, total_amount) VALUES (?, ?, ?)";
    String detailQuery = "INSERT INTO order_details (order_id, product_id, quantity, price, subtotal) VALUES (?, ?, ?, ?, ?)";

    try (Connection con = com.inventory.util.DBConnection.getConnection()) {
        con.setAutoCommit(false); // Start transaction

        // 1. Insert into 'orders' and get the new ID
        PreparedStatement psOrder = con.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
        psOrder.setInt(1, order.getCustomerId());
        psOrder.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        psOrder.setDouble(3, order.getTotalAmount());
        psOrder.executeUpdate();

        ResultSet rs = psOrder.getGeneratedKeys();
        if (rs.next()) {
            generatedOrderId = rs.getInt(1);
        }

        // 2. Insert each row from tblCart into 'order_details'
        PreparedStatement psDetail = con.prepareStatement(detailQuery);
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            psDetail.setInt(1, generatedOrderId);
            psDetail.setInt(2, Integer.parseInt(cartModel.getValueAt(i, 0).toString())); // Product ID
            psDetail.setInt(3, Integer.parseInt(cartModel.getValueAt(i, 2).toString())); // Qty
            psDetail.setDouble(4, Double.parseDouble(cartModel.getValueAt(i, 3).toString())); // Price
            psDetail.setDouble(5, Double.parseDouble(cartModel.getValueAt(i, 4).toString())); // Subtotal
            psDetail.addBatch();
        }
        psDetail.executeBatch();
        
        con.commit(); // Save everything
    } catch (Exception e) {
        e.printStackTrace();
    }
    return generatedOrderId;
}
}
