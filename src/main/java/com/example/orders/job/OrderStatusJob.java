package com.example.orders.job;

import com.example.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled job that updates all PENDING orders to PROCESSING every 5 minutes.
 */
@Component
@EnableScheduling
public class OrderStatusJob {
    @Autowired
    private OrderService orderService;

    /**
     * Runs every 5 minutes to update all PENDING orders to PROCESSING.
     */
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void updatePendingOrders() {
        orderService.updatePendingOrdersToProcessing();
    }
} 