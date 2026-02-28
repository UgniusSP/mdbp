package com.vgtu.mdbp.repository;

import com.vgtu.mdbp.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByDealerId(String dealerId);
    List<Order> findByProductId(String productId);
    List<Order> findByCustomerId(String customerId);
    List<Order> findByStatus(String status);
    List<Order> findByDealerIdAndStatus(String dealerId, String status);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByDealerIdAndOrderDateBetween(String dealerId, LocalDateTime startDate, LocalDateTime endDate);
}

