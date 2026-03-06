/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

/**
 *
 * @author bhaskarjoshi
 */

import com.inventory.model.Category;
import com.inventory.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    /**
     * Retrieves all categories.
     * Perfect for filling up a Dropdown/ComboBox in the UI.
     * @return 
     */
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        // Table: categories | Columns: category_id, category_name
        String query = "SELECT * FROM categories ORDER BY category_id ASC"; 

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id")); 
                category.setCategoryName(rs.getString("category_name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Added for debugging
        }
        return categories;
    }

    public boolean addCategory(String categoryName) {
        String query = "INSERT INTO categories (category_name) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, categoryName);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCategory(int id, String newName) {
        String query = "UPDATE categories SET category_name = ? WHERE category_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(int categoryId) {
        String query = "DELETE FROM categories WHERE category_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, categoryId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int getCategoryIdByName(String name) {
    int id = -1;
    String query = "SELECT category_id FROM categories WHERE category_name = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            id = rs.getInt("category_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return id;
}
    
    //Testing
    
    public static void main(String[] args) {
    CategoryDAO dao = new CategoryDAO();
    
    // Test 1: Fetching Categories
    java.util.List<com.inventory.model.Category> list = dao.getAllCategories();
    
    System.out.println("--- Category Database Test ---");
    if (list.isEmpty()) {
        System.out.println("No categories found. Make sure you ran the INSERT script in MySQL!");
    } else {
        for (com.inventory.model.Category c : list) {
            System.out.println("ID: " + c.getCategoryId() + " | Name: " + c.getCategoryName());
        }
        System.out.println("TEST PASSED: Successfully retrieved " + list.size() + " categories.");
    }

    // Test 2: Adding a new Category
    boolean isAdded = dao.addCategory("Pharmacy");
    if (isAdded) {
        System.out.println("TEST PASSED: Successfully added 'Pharmacy' category.");
    } else {
        System.out.println("TEST FAILED: Could not add category.");
    }
}
}
