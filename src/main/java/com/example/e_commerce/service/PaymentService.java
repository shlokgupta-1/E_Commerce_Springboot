package com.example.e_commerce.service;

import com.example.e_commerce.model.Order;
import com.example.e_commerce.model.OrderItem;
import com.example.e_commerce.model.Payment;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.OrderRepository;
import com.example.e_commerce.repository.PaymentRepository;
import com.example.e_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          OrderRepository orderRepository,
                          ProductRepository productRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // ===============================
    // CREATE PAYMENT (MOCK GATEWAY)
    // ===============================
    public Payment createPayment(String orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(order.getTotalAmount());
        payment.setStatus("PENDING");
        payment.setPaymentId("MOCK_" + UUID.randomUUID());

        return paymentRepository.save(payment);
    }

    // ===============================
    // PAYMENT WEBHOOK (SUCCESS / FAILED)
    // ===============================
    public Payment completePayment(String paymentId, String status) {

        Payment payment = paymentRepository.findAll()
                .stream()
                .filter(p -> p.getPaymentId().equals(paymentId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(status);

        if ("SUCCESS".equalsIgnoreCase(status)) {

            Order order = orderRepository.findById(payment.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            // 🔴 REDUCE INVENTORY HERE (CORRECT PLACE)
            for (OrderItem item : order.getItems()) {

                Product product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                if (product.getStock() < item.getQuantity()) {
                    throw new RuntimeException(
                            "Insufficient stock for product: " + product.getName()
                    );
                }

                product.setStock(product.getStock() - item.getQuantity());
                productRepository.save(product);
            }

            // Update order status
            order.setStatus("PAID");
            orderRepository.save(order);
        }

        return paymentRepository.save(payment);
    }
}
