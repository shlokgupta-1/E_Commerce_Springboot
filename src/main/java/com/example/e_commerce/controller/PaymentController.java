package com.example.e_commerce.controller;

import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.model.Payment;
import com.example.e_commerce.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // POST /api/payments/create
    @PostMapping("/create")
    public Payment createPayment(@RequestBody PaymentRequest request) {
        return paymentService.createPayment(request.getOrderId());
    }
}
