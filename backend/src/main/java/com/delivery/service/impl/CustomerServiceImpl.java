package com.delivery.service.impl;

import com.delivery.model.Customer;
import com.delivery.model.Order;
import com.delivery.repository.CustomerRepository;
import com.delivery.repository.OrderRepository;
import com.delivery.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer login(String email, String password) {
        return customerRepository.findByEmail(email)
                .filter(c -> passwordEncoder.matches(password, c.getPassword()))
                .orElse(null);
    }

    @Override
    public List<Order> getOrdersForCustomer(Long customerId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .toList();
    }
}
