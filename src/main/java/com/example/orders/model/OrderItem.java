package com.example.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an item in an order, including product ID, quantity, and price.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    /** Unique identifier for the product */
    private String productId;
    /** Quantity of the product ordered */
    private int quantity;
    /** Price per unit of the product */
    private double price;
} 