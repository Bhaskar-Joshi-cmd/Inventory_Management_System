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

public class Transaction {
    private int transId;
    private int userId;
    private int productId;
    private String transactionType;
    private int quantityChanged;
    private Date transDate;

    public Transaction() {}

    public Transaction(int transId, int userId, int productId, String transactionType, int quantityChanged, Date transDate) {
        this.transId = transId;
        this.userId = userId;
        this.productId = productId;
        this.transactionType = transactionType;
        this.quantityChanged = quantityChanged;
        this.transDate = transDate;
    }

    // Getters and Setters
    public int getTransId() { return transId; }
    public void setTransId(int transId) { this.transId = transId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public int getQuantityChanged() { return quantityChanged; }
    public void setQuantityChanged(int quantityChanged) { this.quantityChanged = quantityChanged; }

    public Date getTransDate() { return transDate; }
    public void setTransDate(Date transDate) { this.transDate = transDate; }
}
