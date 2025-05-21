package com.example.orders;

import com.example.orders.model.Order;
import com.example.orders.model.OrderItem;
import com.example.orders.model.OrderStatus;
import com.example.orders.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

/**
 * Unit tests for the OrderService class, verifying order creation, retrieval, status updates, listing, cancellation, and scheduled updates.
 */
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    /**
     * Test creating an order and retrieving it by ID.
     */
    @Test
    void testCreateAndGetOrder() {
        OrderItem item = new OrderItem("prod-1", 2, 10.0);
        Order order = orderService.createOrder(List.of(item));
        Optional<Order> fetched = orderService.getOrderById(order.getId());
        Assertions.assertTrue(fetched.isPresent());
        Assertions.assertEquals(order.getId(), fetched.get().getId());
    }

    /**
     * Test updating the status of an order.
     */
    @Test
    void testUpdateOrderStatus() {
        OrderItem item = new OrderItem("prod-2", 1, 20.0);
        Order order = orderService.createOrder(List.of(item));
        orderService.updateOrderStatus(order.getId(), OrderStatus.SHIPPED);
        Optional<Order> updated = orderService.getOrderById(order.getId());
        Assertions.assertEquals(OrderStatus.SHIPPED, updated.get().getStatus());
    }

    /**
     * Test listing all orders.
     */
    @Test
    void testListOrders() {
        OrderItem item1 = new OrderItem("prod-3", 1, 5.0);
        OrderItem item2 = new OrderItem("prod-4", 3, 15.0);
        orderService.createOrder(List.of(item1));
        orderService.createOrder(List.of(item2));
        List<Order> allOrders = orderService.listOrders(Optional.empty());
        Assertions.assertTrue(allOrders.size() >= 2);
    }

    /**
     * Test cancelling an order that is in PENDING status.
     */
    @Test
    void testCancelOrder() {
        OrderItem item = new OrderItem("prod-5", 1, 30.0);
        Order order = orderService.createOrder(List.of(item));
        Optional<Order> cancelled = orderService.cancelOrder(order.getId());
        Assertions.assertTrue(cancelled.isPresent());
        Assertions.assertEquals(OrderStatus.CANCELLED, cancelled.get().getStatus());
    }

    /**
     * Test updating all PENDING orders to PROCESSING using the scheduled job logic.
     */
    @Test
    void testUpdatePendingOrdersToProcessing() {
        OrderItem item = new OrderItem("prod-6", 2, 12.0);
        Order order = orderService.createOrder(List.of(item));
        orderService.updatePendingOrdersToProcessing();
        Optional<Order> updated = orderService.getOrderById(order.getId());
        Assertions.assertEquals(OrderStatus.PROCESSING, updated.get().getStatus());
    }
} 