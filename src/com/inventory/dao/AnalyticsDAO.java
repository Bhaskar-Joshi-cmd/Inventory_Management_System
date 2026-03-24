/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;
import com.inventory.util.DBConnection; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.inventory.model.ProductSales;
/**
 *
 * @author bhaskarjoshi
 */
public class AnalyticsDAO {
    // 1. KPI RIBBON: Total Revenue
    public double getTotalRevenue(int days) {
        double total = 0;
        String query = "SELECT SUM(total_amount) FROM orders WHERE order_date >= DATE_SUB(NOW(), INTERVAL ? DAY)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, days);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // 2. KPI RIBBON: Total Orders
    public int getTotalOrders(int days) {
        int count = 0;
        String query = "SELECT COUNT(order_id) FROM orders WHERE order_date >= DATE_SUB(NOW(), INTERVAL ? DAY)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, days);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // 3. HOT & COLD PRODUCTS: Ranking Logic
    public List<ProductSales> getProductSalesRanking(int days, boolean isAsc) {
    List<ProductSales> salesList = new ArrayList<>();
    String order = isAsc ? "ASC" : "DESC";
    
    // Updated c.category_name to match your DB screenshot
    String query = "SELECT p.name, c.category_name as cat_name, SUM(od.quantity) as total_qty, " +
               "SUM(od.subtotal) as total_revenue " + // Changed from unit_price to subtotal
               "FROM order_details od " +
               "JOIN products p ON od.product_id = p.product_id " + 
               "JOIN categories c ON p.category_id = c.category_id " + 
               "JOIN orders o ON od.order_id = o.order_id " +
               "WHERE o.order_date >= DATE_SUB(NOW(), INTERVAL ? DAY) " +
               "GROUP BY p.product_id, c.category_name " + 
               "ORDER BY total_qty " + order + " LIMIT 5";

    try (Connection con = DBConnection.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, days);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            salesList.add(new ProductSales(
                rs.getString("name"), 
                rs.getString("cat_name"), 
                rs.getInt("total_qty"), 
                rs.getDouble("total_revenue")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return salesList;
}
    
    // 4. KPI RIBBON: Best Category
    public String getBestCategory(int days) {
    String category = "N/A";
    // Updated to SELECT c.category_name
    String query = "SELECT c.category_name FROM order_details od " +
                   "JOIN products p ON od.product_id = p.product_id " +
                   "JOIN categories c ON p.category_id = c.category_id " +
                   "JOIN orders o ON od.order_id = o.order_id " +
                   "WHERE o.order_date >= DATE_SUB(NOW(), INTERVAL ? DAY) " +
                   "GROUP BY c.category_id ORDER BY SUM(od.quantity) DESC LIMIT 1";
    try (Connection con = DBConnection.getConnection(); 
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, days);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            category = rs.getString(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return category;
}
    
}
