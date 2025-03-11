package com.ecommerce.service;
//Order Service
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dto.OrderDetailsDTO;
import com.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.model.OrderDetails;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Products;
import com.ecommerce.model.Users;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

 private final OrderRepository orderRepository;
 private final UserRepository userRepository;
 private final ProductRepository productRepository;
 

 public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
     this.orderRepository = orderRepository;
     this.userRepository = userRepository;
     this.productRepository = productRepository;
     
 }

 public Orders placeOrder(Long userId, Double totalAmount, String status) {
     Users user = userRepository.findById(userId)
             .orElseThrow(() -> new IllegalArgumentException("User not found"));
     Orders order = new Orders(user, LocalDate.now(), totalAmount, status);
     return orderRepository.save(order);
 }

 public List<Orders> getOrdersByUser(Long userId) {
     return orderRepository.findByUserUserId(userId);
 }

 public Optional<Orders> getOrderById(Long orderId) {
     return Optional.of(orderRepository.findById(orderId)
    		 .orElseThrow(() -> new IllegalArgumentException("Order not found")));
 }

 public Orders updateOrderStatus(Long orderId, String status) {
     Orders order = orderRepository.findById(orderId)
             .orElseThrow(() -> new IllegalArgumentException("Order not found"));
     order.setStatus(status);
     return orderRepository.save(order);
 }

 public void deleteOrder(Long orderId) {
     orderRepository.deleteById(orderId);
 }
 
 @Transactional
 public Orders placeOrder(OrderRequestDTO orderRequest) {
     // Fetch the user
     Users user = userRepository.findById(orderRequest.getUserId())
             .orElseThrow(() -> new RuntimeException("User not found"));

     Orders order = new Orders();
     order.setUser(user);
     order.setOrderDate(LocalDate.now());
     order.setStatus(orderRequest.getStatus());

     double totalAmount = 0.0;

     for (OrderDetailsDTO detailDTO : orderRequest.getOrderDetails()) {
         // Fetch the product and lock row for update (prevent overselling)
         Products product = productRepository.findById(detailDTO.getProductId())
                 .orElseThrow(() -> new RuntimeException("Product not found"));

         int requestedQuantity = detailDTO.getQuantity();
         
         //  Check if enough stock is available
         if (product.getStockQuantity() < requestedQuantity) {
             throw new RuntimeException("Insufficient stock for product: " + product.getName());
         }

         //  Reserve stock (deduct quantity)
         product.setStockQuantity(product.getStockQuantity() - requestedQuantity);
         productRepository.save(product);

         // Calculate price using database value
         double pricePerUnit = product.getPrice();
         double lineTotal = pricePerUnit * requestedQuantity;
         totalAmount += lineTotal;

         // Create OrderDetails object
         OrderDetails detail = new OrderDetails();
         detail.setOrder(order);
         detail.setProduct(product);
         detail.setQuantity(requestedQuantity);
         detail.setPricePerUnit(pricePerUnit);
         order.addOrderDetail(detail);
     }

     // Set total amount on the order
     order.setTotalAmount(totalAmount);

     // Create Payment object
     /*Payments payment = new Payments();
     payment.setOrder(order);
     payment.setPaymentMethod(orderRequest.getPayment().getPaymentMethod());
     payment.setPaymentStatus(orderRequest.getPayment().getPaymentStatus());
     payment.setAmount(totalAmount);
     payment.setTransactionId(orderRequest.getPayment().getTransactionId());
     order.setPayment(payment);*/

     //  Save the order and update stock atomically
     return orderRepository.save(order);
 }
 
 
	 

}
