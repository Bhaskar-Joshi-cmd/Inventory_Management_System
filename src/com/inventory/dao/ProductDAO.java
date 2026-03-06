/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

/**
 *
 * @author bhaskarjoshi
 */

import com.inventory.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.inventory.util.DBConnection;
import com.inventory.model.Order;

public class ProductDAO {

    /**
     * Fetch all products from the database.
     * Useful for filling up the JTable in your UI.
     */
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, c.category_name FROM products p " +
                       "JOIN categories c ON p.category_id = c.category_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setCurrentQuantity(rs.getInt("current_quantity"));
                product.setMinThreshold(rs.getInt("min_threshold"));
                product.setExpiryDate(rs.getDate("expiry_date"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products:");
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Add a new product to the database.
     */
    public boolean addProduct(Product product) {
        String query = "INSERT INTO products (name, price, current_quantity, min_threshold, expiry_date, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getCurrentQuantity());
            pstmt.setInt(4, product.getMinThreshold());
            // Converting Java Date to SQL Date
            pstmt.setDate(5, new java.sql.Date(product.getExpiryDate().getTime()));
            pstmt.setInt(6, product.getCategoryId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding product:");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update an existing product.
     */
    public boolean updateProduct(Product product) {
        String query = "UPDATE products SET name=?, price=?, current_quantity=?, min_threshold=?, expiry_date=?, category_id=? WHERE product_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getCurrentQuantity());
            pstmt.setInt(4, product.getMinThreshold());
            pstmt.setDate(5, new java.sql.Date(product.getExpiryDate().getTime()));
            pstmt.setInt(6, product.getCategoryId());
            pstmt.setInt(7, product.getProductId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating product:");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a product by its ID.
     */
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, productId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting product:");
            e.printStackTrace();
            return false;
        }
    }
    
    public void updateStock(int productId, int quantitySold) {
    String query = "UPDATE products SET current_quantity = current_quantity - ? WHERE product_id = ?";
    try (java.sql.Connection con = com.inventory.util.DBConnection.getConnection();
     java.sql.PreparedStatement ps = con.prepareStatement(query)) {
    
    ps.setInt(1, quantitySold);
    ps.setInt(2, productId);
    ps.executeUpdate();
    
} catch (Exception e) {
    e.printStackTrace();
}
}
    
    public List<Order> getOrdersByCustomer(int customerId) {
    List<Order> orders = new ArrayList<>();
    String query = "SELECT * FROM orders WHERE customer_id = ?";
    try (Connection con = com.inventory.util.DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Order o = new Order();
            o.setOrderId(rs.getInt("order_id"));
            o.setOrderDate(rs.getDate("order_date"));
            o.setTotalAmount(rs.getDouble("total_amount"));
            orders.add(o);
        }
    } catch (Exception e) { e.printStackTrace(); }
    return orders;
}
    
    //Testing
    
    
    public static void main(String[] args) {
    ProductDAO dao = new ProductDAO();
    java.util.List<com.inventory.model.Product> list = dao.getAllProducts();

    System.out.println("--- Product Database Test ---");
    if (list.isEmpty()) {
        System.out.println("No products found. Did you run the INSERT script?");
    } else {
        for (com.inventory.model.Product p : list) {
            System.out.println("ID: " + p.getProductId() + " | Name: " + p.getName() + " | Price: " + p.getPrice());
        }
        System.out.println("TEST PASSED: Successfully retrieved " + list.size() + " products.");
    }
}
    
    public com.inventory.model.Product getProductById(int id) {
    com.inventory.model.Product p = new com.inventory.model.Product();
    // Use the exact same query style as your getAllProducts
    String query = "SELECT p.*, c.category_name FROM products p " +
                   "JOIN categories c ON p.category_id = c.category_id " +
                   "WHERE p.product_id = ?";
    
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {
        
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            p.setProductId(rs.getInt("product_id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getDouble("price"));
            
            // FIX: Use 'current_quantity' instead of 'quantity'
            p.setCurrentQuantity(rs.getInt("current_quantity")); 
            
            // FIX: Use 'min_threshold' instead of 'threshold'
            p.setMinThreshold(rs.getInt("min_threshold")); 
            
            p.setExpiryDate(rs.getDate("expiry_date"));
            p.setCategoryName(rs.getString("category_name")); 
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return p;
}
    
}
