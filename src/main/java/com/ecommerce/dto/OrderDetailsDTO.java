package com.ecommerce.dto;

//OrderItemDTO.java
public class OrderDetailsDTO {
 private Long productId;
 private Integer quantity;
 private Double pricePerUnit;

 // Getters and Setters
 public Long getProductId() { return productId; }
 public void setProductId(Long productId) { this.productId = productId; }

 public Integer getQuantity() { return quantity; }
 public void setQuantity(Integer quantity) { this.quantity = quantity; }

 public Double getPricePerUnit() { return pricePerUnit; }
 public void setPricePerUnit(Double pricePerUnit) { this.pricePerUnit = pricePerUnit; }
}