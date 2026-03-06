/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.model;

/**
 *
 * @author bhaskarjoshi
 */

/**
 * Model class representing the Categories table in MySQL.
 * This class is a "Data Carrier" used to pass category information 
 * between the Database and the UI.
 */
public class Category {
    private int categoryId;
    private String categoryName;

    /**
     * Default Constructor 
     * Required for various Java frameworks and flexibility.
     */
    public Category() {
    }

    /**
     * Parameterized Constructor
     * Used to quickly create a Category object with data from the database.
     * * @param categoryId Unique ID from the database
     * @param categoryName Name of the category (e.g., Electronics, Groceries)
     */
    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // --- Getters and Setters ---
    // These methods allow other classes (like your DAO) to read and write data safely.

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
