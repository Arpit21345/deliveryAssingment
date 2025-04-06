package com.delivery.service.impl;

import com.delivery.model.DeliveryPerson;
import com.delivery.model.Order;
import com.delivery.repository.DeliveryPersonRepository;
import com.delivery.repository.OrderRepository;
import com.delivery.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryPersonRepository deliveryPersonRepository;

    @Override
    public Order createOrder(Order order) {
        order.setDeliveryStatus("pending");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public boolean assignDeliveryPerson(String orderId) {
        List<DeliveryPerson> available = deliveryPersonRepository.findByStatus("available");
        if (available.isEmpty()) return false;

        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) return false;

        DeliveryPerson person = available.get(0); // take the first available
        person.setStatus("busy");
        deliveryPersonRepository.save(person);

        order.setDeliveryPerson(person);
        order.setDeliveryStatus("assigned");
        orderRepository.save(order);

        return true;
    }
}
