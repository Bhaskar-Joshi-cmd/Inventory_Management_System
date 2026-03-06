/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.model;

/**
 *
 * @author bhaskarjoshi
 */
import java.util.Date;

public class Product {
    private int productId;
    private String name;
    private double price;
    private int currentQuantity;
    private int minThreshold;
    private Date expiryDate;
    private int categoryId;
    private String categoryName;

    // Default Constructor
    public Product() {}

    // Parameterized Constructor
    public Product(int productId, String name, double price, int currentQuantity, int minThreshold, Date expiryDate, int categoryId) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.currentQuantity = currentQuantity;
        this.minThreshold = minThreshold;
        this.expiryDate = expiryDate;
        this.categoryId = categoryId;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getMinThreshold() {
        return minThreshold;
    }

    public void setMinThreshold(int minThreshold) {
        this.minThreshold = minThreshold;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

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
