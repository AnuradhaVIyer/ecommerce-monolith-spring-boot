package com.ecommerce.dto;

//OrderRequestDTO.java
import java.util.List;

public class OrderRequestDTO {
 private Long userId;
 private List<OrderDetailsDTO> orderDetails; // List of products in the order
 private String status = "PENDING";            // Order status (e.g., PENDING, SHIPPED)
 private PaymentDTO payment; // Add this field

 public OrderRequestDTO() {}

 // Getters and Setters
 public Long getUserId() { return userId; }
 public void setUserId(Long userId) { this.userId = userId; }

 public List<OrderDetailsDTO> getOrderDetails() { return orderDetails; }
 public void setOrderDetails(List<OrderDetailsDTO> orderDetails) { this.orderDetails = orderDetails; }

 public String getStatus() { return status; }
 public void setStatus(String status) { this.status = status; }
 
 
public PaymentDTO getPayment() {
	return payment;
}

public void setPayment(PaymentDTO payment) {
	this.payment = payment;
}

//Calculate total amount from order details
 public Double getTotalAmount() {
     return orderDetails.stream()
             .mapToDouble(detail -> detail.getQuantity() * detail.getPricePerUnit())
             .sum();
 }
 
}

