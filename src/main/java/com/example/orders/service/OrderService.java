package com.example.orders.service;

import com.example.orders.model.Order;
import com.example.orders.model.OrderItem;
import com.example.orders.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Service class for managing orders in the E-commerce system.
 * Provides methods to create, retrieve, update, list, and cancel orders.
 * Uses in-memory storage for demonstration purposes.
 */
@Service
public class OrderService {
    private final Map<String, Order> orderStore = new ConcurrentHashMap<>();

    /**
     * Creates a new order with the given list of items. The order is initialized with PENDING status.
     * @param items List of items to be included in the order
     * @return The created Order object
     */
    public Order createOrder(List<OrderItem> items) {
        String id = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order(id, items, OrderStatus.PENDING, now, now);
        orderStore.put(id, order);
        return order;
    }

    /**
     * Retrieves an order by its unique ID.
     * @param id The order ID
     * @return Optional containing the Order if found, or empty if not found
     */
    public Optional<Order> getOrderById(String id) {
        return Optional.ofNullable(orderStore.get(id));
    }

    /**
     * Updates the status of an order, unless it is already cancelled.
     * @param id The order ID
     * @param status The new status to set
     * @return Optional containing the updated Order if successful, or empty if not found or cancelled
     */
    public Optional<Order> updateOrderStatus(String id, OrderStatus status) {
        Order order = orderStore.get(id);
        if (order != null && order.getStatus() != OrderStatus.CANCELLED) {
            order.setStatus(status);
            order.setUpdatedAt(LocalDateTime.now());
            return Optional.of(order);
        }
        return Optional.empty();
    }

    /**
     * Lists all orders, optionally filtered by status.
     * @param status Optional status to filter orders
     * @return List of orders matching the filter
     */
    public List<Order> listOrders(Optional<OrderStatus> status) {
        return orderStore.values().stream()
                .filter(order -> status.map(s -> order.getStatus() == s).orElse(true))
                .collect(Collectors.toList());
    }

    /**
     * Cancels an order if it is still in PENDING status.
     * @param id The order ID
     * @return Optional containing the cancelled Order if successful, or empty if not found or not PENDING
     */
    public Optional<Order> cancelOrder(String id) {
        Order order = orderStore.get(id);
        if (order != null && order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.CANCELLED);
            order.setUpdatedAt(LocalDateTime.now());
            return Optional.of(order);
        }
        return Optional.empty();
    }

    /**
     * Updates all orders with PENDING status to PROCESSING. Intended to be called by a scheduled job.
     */
    public void updatePendingOrdersToProcessing() {
        orderStore.values().forEach(order -> {
            if (order.getStatus() == OrderStatus.PENDING) {
                order.setStatus(OrderStatus.PROCESSING);
                order.setUpdatedAt(LocalDateTime.now());
            }
        });
    }
} 