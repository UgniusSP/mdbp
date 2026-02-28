package com.vgtu.mdbp.service;

import com.vgtu.mdbp.model.Order;
import com.vgtu.mdbp.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        if (order.getId() == null) {
            order.setId(UUID.randomUUID());
        }
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByDealerId(UUID dealerId) {
        return orderRepository.findByDealerId(dealerId);
    }

    public Order updateOrder(UUID id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}

