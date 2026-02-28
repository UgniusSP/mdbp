package com.vgtu.mdbp.service;

import com.vgtu.mdbp.dto.OrderDto;
import com.vgtu.mdbp.model.Order;
import com.vgtu.mdbp.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(OrderDto orderDto) {

        List<Order.OrderItem> orderItems = orderDto.getItems()
                .stream()
                .map(itemDto -> new Order.OrderItem(
                        itemDto.getProductId(),
                        itemDto.getProductName(),
                        itemDto.getUnitPrice(),
                        itemDto.getQuantity()
                ))
                .toList();

        Order orderEntity = new Order(
                orderDto.getDealerId(),
                orderItems,
                orderDto.getTotalAmount(),
                orderDto.getStatus()
        );

        return orderRepository.save(orderEntity);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByDealerId(String dealerId) {
        return orderRepository.findByDealerId(dealerId);
    }

    public Order updateOrder(String id, OrderDto orderDto) {

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<Order.OrderItem> updatedItems = orderDto.getItems()
                .stream()
                .map(itemDto -> new Order.OrderItem(
                        itemDto.getProductId(),
                        itemDto.getProductName(),
                        itemDto.getUnitPrice(),
                        itemDto.getQuantity()
                ))
                .toList();

        existingOrder.setItems(updatedItems);
        existingOrder.setTotalAmount(orderDto.getTotalAmount());
        existingOrder.setStatus(orderDto.getStatus());

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}

