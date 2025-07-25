package com.cloud.ecommerce.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @GetMapping
    public List<String> getOrders() {
        return Arrays.asList("Order 1", "Order 2", "Order 3");
    }
}
