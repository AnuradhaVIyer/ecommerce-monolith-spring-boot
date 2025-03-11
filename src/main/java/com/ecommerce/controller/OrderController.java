package com.ecommerce.controller;

//Order Controller
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.model.Orders;
import com.ecommerce.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

 private final OrderService orderService;

 public OrderController(OrderService orderService) {
     this.orderService = orderService;
 }

	/*
	 * @PostMapping("/create") public ResponseEntity<Orders>
	 * createOrder(@RequestParam Long userId,
	 * 
	 * @RequestParam Double totalAmount,
	 * 
	 * @RequestParam String status) { Orders order = orderService.placeOrder(userId,
	 * totalAmount, status); return ResponseEntity.ok(order);
	 
 }*/
 
 @PostMapping("/create")
 public ResponseEntity<Orders> createOrder(@RequestBody OrderRequestDTO orderRequest) {
     Orders order = orderService.placeOrder(orderRequest);
     return ResponseEntity.ok(order);
 }
 

 @GetMapping("/user/{userId}")
 public ResponseEntity<List<Orders>> getOrdersByUser(@PathVariable Long userId) {
     return ResponseEntity.ok(orderService.getOrdersByUser(userId));
 }

 @GetMapping("/{orderId}")
 public ResponseEntity<Orders> getOrderById(@PathVariable Long orderId) {
     return orderService.getOrderById(orderId)
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
 }

 @PutMapping("/{orderId}/update-status")
 public ResponseEntity<Orders> updateOrderStatus(@PathVariable Long orderId,
                                                @RequestParam String status) {
     return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
 }

 @DeleteMapping("/{orderId}")
 public ResponseEntity<Map<String, String>> deleteOrder(@PathVariable Long orderId) {
     orderService.deleteOrder(orderId);
     Map<String, String> response = new HashMap<String, String>();
     response.put("message", "Order with ID " + orderId + " deleted successfully.");
     return ResponseEntity.ok(response);
 }
}
