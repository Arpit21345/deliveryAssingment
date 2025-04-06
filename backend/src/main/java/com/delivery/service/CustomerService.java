package com.delivery.service;

import com.delivery.model.Customer;
import com.delivery.model.Order;

import java.util.List;

public interface CustomerService {
    Customer login(String email, String password);
    List<Order> getOrdersForCustomer(Long customerId);
}
