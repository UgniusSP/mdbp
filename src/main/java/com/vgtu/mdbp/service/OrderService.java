package com.vgtu.mdbp.service;

import com.vgtu.mdbp.model.Order;
import com.vgtu.mdbp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByDealer(String dealerId) {
        return orderRepository.findByDealerId(dealerId);
    }

    public List<Order> getOrdersByProduct(String productId) {
        return orderRepository.findByProductId(productId);
    }

    public List<Order> getOrdersByCustomer(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }


    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByDealerAndStatus(String dealerId, String status) {
        return orderRepository.findByDealerIdAndStatus(dealerId, status);
    }

    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    public List<Order> getOrdersByDealerAndDateRange(String dealerId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByDealerIdAndOrderDateBetween(dealerId, startDate, endDate);
    }

    public Order updateOrder(String id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setId(id);
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean deleteOrder(String id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    public Double getTotalRevenueByDealer(String dealerId) {
        return orderRepository.findByDealerId(dealerId)
                .stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    public Integer getTotalOrdersByDealer(String dealerId) {
        return orderRepository.findByDealerId(dealerId).size();
    }

    public Double getAverageOrderValueByDealer(String dealerId) {
        List<Order> orders = orderRepository.findByDealerId(dealerId);
        if (orders.isEmpty()) {
            return 0.0;
        }
        return orders.stream()
                .mapToDouble(Order::getTotalPrice)
                .average()
                .orElse(0.0);
    }

    public Integer getTotalQuantitySold(String dealerId) {
        return orderRepository.findByDealerId(dealerId)
                .stream()
                .mapToInt(Order::getQuantity)
                .sum();
    }
}

