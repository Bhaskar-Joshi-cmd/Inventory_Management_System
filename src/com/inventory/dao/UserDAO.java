/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

import com.inventory.model.User;
import com.inventory.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Data Access Object for User operations
 * @author bhaskarjoshi
 */
public class UserDAO {

    /**
     * Checks database for matching credentials
     * @param username The email of the user
     * @param password The password
     * @return User object if successful, null otherwise
     */
    public User login(String username, String password) {
        User user = null;
        String query = "SELECT * FROM app_user WHERE email = ? AND password = ? AND status = 'Active'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("app_user_pk"));
                user.setUsername(rs.getString("name"));
                user.setEmail(rs.getString("email")); // 
                user.setRole(rs.getString("user_role"));
                user.setStatus(rs.getString("status")); 
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Login Error: " + e.getMessage());
        }
        return user;
    }

    /**
     * Adds a new user to the database
     */
    public boolean addUser(User user) {
        String query = "INSERT INTO app_user (name, user_role, email, password, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getRole());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getStatus());
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Add User Error: " + e.getMessage());
            return false;
        }
    }
    
    
    public boolean deleteUser(int userId) {
    String query = "DELETE FROM app_user WHERE app_user_pk = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setInt(1, userId);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Delete Error: " + e.getMessage());
        return false;
    }
}
    
    public boolean updateUser(com.inventory.model.User user) {
    String query = "UPDATE app_user SET name=?, user_role=?, email=?, status=? WHERE app_user_pk=?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getRole());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getStatus());
        pstmt.setInt(5, user.getUserId());
        
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Update Error: " + e.getMessage());
        return false;
    }
}

    /**
     * Fetches all users for the JTable
     */
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM app_user";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("app_user_pk"));
                u.setUsername(rs.getString("name"));
                u.setRole(rs.getString("user_role"));
                u.setEmail(rs.getString("email"));
                u.setStatus(rs.getString("status")); 
                list.add(u);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Table Load Error: " + e.getMessage());
        }
        return list;
    }

    // Main method for testing
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User result = dao.login("superadmin@testemail.com", "admin");
        
        if (result != null) {
            System.out.println("TEST 1 PASSED: Found User: " + result.getUsername());
        } else {
            System.out.println("TEST 1 FAILED: Check your MySQL table data!");
        }
    }
}