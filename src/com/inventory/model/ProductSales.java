/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.model;

/**
 *
 * @author bhaskarjoshi
 */
public class ProductSales {
    private String productName;
    private String categoryName; 
    private int quantitySold;
    private double revenueGenerated;

    // Updated Constructor
    public ProductSales(String productName, String categoryName, int quantitySold, double revenueGenerated) {
        this.productName = productName;
        this.categoryName = categoryName;
        this.quantitySold = quantitySold;
        this.revenueGenerated = revenueGenerated;
    }

    // Getters and Setters
    public String getProductName() { return productName; }
    public String getCategoryName() { return categoryName; } // Added
    public int getQuantitySold() { return quantitySold; }
    public double getRevenueGenerated() { return revenueGenerated; }
    
}
