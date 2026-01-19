package com.example.e_commerce.controller;

import com.example.e_commerce.dto.CreateOrderRequest;
import com.example.e_commerce.model.Order;
import com.example.e_commerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /api/orders/place
    @PostMapping("/place")
    public Order placeOrder(@RequestBody CreateOrderRequest request) {
        return orderService.placeOrder(request.getUserId());
    }

    // GET /api/orders/{userId}
    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable String userId) {
        return orderService.getOrdersByUser(userId);
    }
}
