package com.delivery.controller;

import com.delivery.model.Order;
import com.delivery.dto.LoginRequest;
import com.delivery.service.AdminService;
import com.delivery.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")

public class AdminController {

    private final OrderService orderService;
    private final AdminService adminService;

    public AdminController(OrderService orderService, AdminService adminService) {
        this.orderService = orderService;
        this.adminService = adminService;
    }

    // ✅ Admin login using request body (clean way)
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody LoginRequest request) {
        boolean isValid = adminService.login(request.getEmail(), request.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    // ✅ Add order
    @PostMapping("/order")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // ✅ View all orders
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // ✅ Assign delivery
    @PostMapping("/assign/{orderId}")
    public ResponseEntity<String> assignDelivery(@PathVariable String orderId) {
        boolean assigned = orderService.assignDeliveryPerson(orderId);
        return assigned ? ResponseEntity.ok("Delivery person assigned")
                        : ResponseEntity.status(400).body("No delivery person available or order not found");
    }
}
