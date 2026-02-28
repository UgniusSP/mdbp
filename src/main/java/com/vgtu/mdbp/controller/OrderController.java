package com.vgtu.mdbp.controller;

import com.vgtu.mdbp.model.Order;
import com.vgtu.mdbp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<Order>> getOrdersByDealer(@PathVariable String dealerId) {
        List<Order> orders = orderService.getOrdersByDealer(dealerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Order>> getOrdersByProduct(@PathVariable String productId) {
        List<Order> orders = orderService.getOrdersByProduct(productId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@RequestParam String customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping("/search/status")
    public ResponseEntity<List<Order>> getOrdersByStatus(@RequestParam String status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/dealer/{dealerId}/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByDealerAndStatus(@PathVariable String dealerId, @PathVariable String status) {
        List<Order> orders = orderService.getOrdersByDealerAndStatus(dealerId, status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/search/date-range")
    public ResponseEntity<List<Order>> getOrdersByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<Order> orders = orderService.getOrdersByDateRange(start, end);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/dealer/{dealerId}/date-range")
    public ResponseEntity<List<Order>> getOrdersByDealerAndDateRange(
            @PathVariable String dealerId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<Order> orders = orderService.getOrdersByDealerAndDateRange(dealerId, start, end);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        if (orderService.deleteOrder(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllOrders() {
        orderService.deleteAllOrders();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/dealer/{dealerId}/stats/revenue")
    public ResponseEntity<Double> getTotalRevenueByDealer(@PathVariable String dealerId) {
        Double revenue = orderService.getTotalRevenueByDealer(dealerId);
        return new ResponseEntity<>(revenue, HttpStatus.OK);
    }

    @GetMapping("/dealer/{dealerId}/stats/order-count")
    public ResponseEntity<Integer> getTotalOrdersByDealer(@PathVariable String dealerId) {
        Integer count = orderService.getTotalOrdersByDealer(dealerId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/dealer/{dealerId}/stats/average-order-value")
    public ResponseEntity<Double> getAverageOrderValueByDealer(@PathVariable String dealerId) {
        Double avgValue = orderService.getAverageOrderValueByDealer(dealerId);
        return new ResponseEntity<>(avgValue, HttpStatus.OK);
    }

    @GetMapping("/dealer/{dealerId}/stats/quantity-sold")
    public ResponseEntity<Integer> getTotalQuantitySold(@PathVariable String dealerId) {
        Integer quantity = orderService.getTotalQuantitySold(dealerId);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }
}

