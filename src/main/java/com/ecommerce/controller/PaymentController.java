package com.ecommerce.controller;

import org.springframework.http.ResponseEntity;
//Payment Controller
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dto.PaymentRequest;
import com.ecommerce.model.Payments;
import com.ecommerce.service.PaymentService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payments> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payments getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public Payments createPayment(@RequestBody Payments payment, @RequestParam Long userId) {
        return paymentService.createPayment(payment, userId);
    }

    @PutMapping("/{id}")
    public Payments updatePayment(@PathVariable Long id, @RequestBody Payments payment, @RequestParam Long userId) {
        return paymentService.updatePayment(id, payment, userId);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id, @RequestParam Long userId) {
        paymentService.deletePayment(id, userId);
    }
    
    @PostMapping("/process")
    public ResponseEntity<Payments> processPayment(@Valid @RequestBody PaymentRequest request) {
        Payments payment = paymentService.processPayment(request);
        return ResponseEntity.ok(payment);
    }
}