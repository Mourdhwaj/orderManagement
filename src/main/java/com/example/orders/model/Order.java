package com.example.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a customer order in the E-commerce system.
 * Contains order ID, list of items, status, and timestamps.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /** Unique identifier for the order */
    private String id;
    /** List of items included in the order */
    private List<OrderItem> items;
    /** Current status of the order */
    private OrderStatus status;
    /** Timestamp when the order was created */
    private LocalDateTime createdAt;
    /** Timestamp when the order was last updated */
    private LocalDateTime updatedAt;
} 