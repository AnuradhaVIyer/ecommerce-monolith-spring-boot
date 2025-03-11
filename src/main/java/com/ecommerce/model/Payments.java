//Payment Entity

package com.ecommerce.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Payments {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long paymentId;

 @OneToOne
 @JoinColumn(name = "order_id", nullable = false)
 @JsonBackReference
 private Orders order;

 private String paymentMethod;  // CARD, UPI, PAYPAL
 private String paymentStatus = "PENDING";
 private String transactionId;
 private Double amount;
 private LocalDateTime createdAt = LocalDateTime.now();

 // Constructors
 public Payments() {}

 public Payments(Orders order, String paymentMethod, Double amount, String transactionId) {
     this.order = order;
     this.paymentMethod = paymentMethod;
     this.amount = amount;
     this.transactionId = transactionId;
 }

 // Getters and Setters
 public Long getPaymentId() { return paymentId; }
 public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

 public Orders getOrder() { return order; }
 public void setOrder(Orders order) { this.order = order; }

 public String getPaymentMethod() { return paymentMethod; }
 public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

 public String getPaymentStatus() { return paymentStatus; }
 public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

 public String getTransactionId() { return transactionId; }
 public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

public Double getAmount() {
	return amount;
}

public void setAmount(Double amount) {
	this.amount = amount;
}

public LocalDateTime getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}
 
 
}