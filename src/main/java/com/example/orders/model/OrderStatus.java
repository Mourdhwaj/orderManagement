package com.example.orders.model;

/**
 * Enum representing the possible statuses of an order in the E-commerce system.
 */
public enum OrderStatus {
    /** Order has been placed but not yet processed */
    PENDING,
    /** Order is currently being processed */
    PROCESSING,
    /** Order has been shipped to the customer */
    SHIPPED,
    /** Order has been delivered to the customer */
    DELIVERED,
    /** Order has been cancelled */
    CANCELLED
} 