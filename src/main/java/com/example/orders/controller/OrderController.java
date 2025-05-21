package com.example.orders.controller;

import com.example.orders.model.Order;
import com.example.orders.model.OrderItem;
import com.example.orders.model.OrderStatus;
import com.example.orders.service.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for handling order-related HTTP requests.
 * Provides endpoints to create, retrieve, update, list, and cancel orders.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * Creates a new order with the provided list of items.
     * @param request The request body containing the list of items
     * @return The created Order
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(request.getItems());
        return ResponseEntity.ok(order);
    }

    /**
     * Retrieves the details of an order by its ID.
     * @param id The order ID
     * @return The Order if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates the status of an order.
     * @param id The order ID
     * @param request The request body containing the new status
     * @return The updated Order if successful, or 404 if not found
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String id, @RequestBody UpdateStatusRequest request) {
        return orderService.updateOrderStatus(id, request.getStatus())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lists all orders, optionally filtered by status.
     * @param status Optional status to filter orders
     * @return List of orders matching the filter
     */
    @GetMapping
    public List<Order> listOrders(@RequestParam(required = false) OrderStatus status) {
        return orderService.listOrders(Optional.ofNullable(status));
    }

    /**
     * Cancels an order if it is still in PENDING status.
     * @param id The order ID
     * @return The cancelled Order if successful, or 400 if not allowed
     */
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable String id) {
        return orderService.cancelOrder(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * DTO for creating an order. Contains a list of items.
     */
    @Data
    public static class CreateOrderRequest {
        private List<OrderItem> items;
    }

    /**
     * DTO for updating the status of an order.
     */
    @Data
    public static class UpdateStatusRequest {
        private OrderStatus status;
    }
} 