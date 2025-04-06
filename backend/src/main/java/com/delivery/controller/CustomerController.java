package com.delivery.controller;

import com.delivery.model.Customer;
import com.delivery.model.Order;
import com.delivery.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestParam String email, @RequestParam String password) {
        Customer customer = customerService.login(email, password);
        return (customer != null)
                ? ResponseEntity.ok(customer)
                : ResponseEntity.status(401).build();
    }

    @GetMapping("/orders/{customerId}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getOrdersForCustomer(customerId));
    }
}
