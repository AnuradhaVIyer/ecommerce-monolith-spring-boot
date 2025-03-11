package com.ecommerce.controller;

import org.springframework.http.ResponseEntity;
//User Controller
import org.springframework.web.bind.annotation.*;

import com.ecommerce.model.Users;
import com.ecommerce.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

 private final UserService userService;

 public UserController(UserService userService) {
     this.userService = userService;
 }

 @GetMapping
 public List<Users> getAllUsers() {
     return userService.getAllUsers();
 }

 @GetMapping("/{id}")
 public Users getUserById(@PathVariable Long id) {
     return userService.getUserById(id);
 }

 @PostMapping
 public Users createUser(@RequestBody Users user) {
     return userService.saveUser(user);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
     userService.deleteUser(id);
     Map<String, String> response = new HashMap<String, String>();
     response.put("message", "User with ID " + id + " deleted successfully.");
     return ResponseEntity.ok(response);
 }

 @PostMapping("/register")
 public Users registerUser(@RequestBody Users user) {
     return userService.registerUser(user);
 }

 @PostMapping("/login")
 public Users loginUser(@RequestParam String email, @RequestParam String password) {
     return userService.loginUser(email, password);
 }
 
 @GetMapping("/logout")
 public String logout() {
     return "redirect:/index";
 }
}