package com.example.e_commerce.webhook;

import com.example.e_commerce.dto.PaymentWebhookRequest;
import com.example.e_commerce.model.Payment;
import com.example.e_commerce.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
public class PaymentWebhookController {

    private final PaymentService paymentService;

    public PaymentWebhookController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // POST /api/webhooks/payment
    @PostMapping("/payment")
    public Payment handlePayment(@RequestBody PaymentWebhookRequest request) {
        return paymentService.completePayment(
                request.getPaymentId(),
                request.getStatus()
        );
    }
}
