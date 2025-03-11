package com.ecommerce.service;

//Payment Service
import org.springframework.stereotype.Service;

import com.ecommerce.dto.PaymentRequest;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Payments;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, UserService userService, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    public List<Payments> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payments getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payments createPayment(Payments payment, Long userId) {
        if (!userService.isAdmin(userId)) {
            throw new RuntimeException("Access Denied: Admins Only");
        }
        return paymentRepository.save(payment);
    }

    public Payments updatePayment(Long id, Payments updatedPayment, Long userId) {
        if (!userService.isAdmin(userId)) {
            throw new RuntimeException("Access Denied: Admins Only");
        }
        return paymentRepository.findById(id).map(payment -> {
            payment.setPaymentMethod(updatedPayment.getPaymentMethod());
            payment.setPaymentStatus(updatedPayment.getPaymentStatus());
            payment.setTransactionId(updatedPayment.getTransactionId());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public void deletePayment(Long id, Long userId) {
        if (!userService.isAdmin(userId)) {
            throw new RuntimeException("Access Denied: Admins Only");
        }
        paymentRepository.deleteById(id);
    }
    
    public Payments processPayment(PaymentRequest request) {
        Orders order = orderRepository.findById(request.getOrderId())
            .orElseThrow(() -> new RuntimeException("Order not found"));

        Payments payment = new Payments(order, request.getPaymentMethod(), 
                                      request.getAmount(), request.getTransactionId());

        payment.setPaymentStatus("SUCCESS");

        // Update order status on successful payment
        order.setStatus("CONFIRMED");
        order.setPayment(payment);
        //orderRepository.save(order);

        return paymentRepository.save(payment);
    }
}