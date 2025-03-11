//Order Entity
package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Orders {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long orderId;

 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 private Users user;

 @NotNull(message = "Order date cannot be null")
 private LocalDate orderDate;

 @NotNull(message = "Total amount cannot be null")
 @Positive(message = "Total amount must be positive")
 private Double totalAmount;

 @NotBlank(message = "Status cannot be blank")
 @Size(max = 50, message = "Status must be within 50 characters")
 private String status = "PENDING";

@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonManagedReference
 private List<OrderDetails> orderDetails = new ArrayList<>(); // Ensure this is initialized;

 @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
 @JsonManagedReference
 private Payments payment;
 
 // Constructors
 public Orders() {}

 public Orders(Users user, LocalDate orderDate, Double totalAmount, String status) {
     this.user = user;
     this.orderDate = orderDate;
     this.totalAmount = totalAmount;
     this.status = status;
 }

 // Getters and Setters
 public Long getOrderId() { return orderId; }
 public void setOrderId(Long orderId) { this.orderId = orderId; }

 public Users getUser() { return user; }
 public void setUser(Users user) { this.user = user; }

 public LocalDate getOrderDate() { return orderDate; }
 public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

 public Double getTotalAmount() { return totalAmount; }
 public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

 public String getStatus() { return status; }
 public void setStatus(String status) { this.status = status; }

 public List<OrderDetails> getOrderDetails() { return orderDetails; }
 public void setOrderDetails(List<OrderDetails> orderDetails) { this.orderDetails = orderDetails; }
 
//Helper method to add OrderDetails
 public void addOrderDetail(OrderDetails orderDetail) {
     orderDetails.add(orderDetail);
     orderDetail.setOrder(this); // Maintain the bidirectional link
 }

public Payments getPayment() {
	return payment;
}

public void setPayment(Payments payment) {
	this.payment = payment;
}


 
}
