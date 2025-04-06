package com.delivery.service;

import com.delivery.model.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    boolean assignDeliveryPerson(String orderId);
}
