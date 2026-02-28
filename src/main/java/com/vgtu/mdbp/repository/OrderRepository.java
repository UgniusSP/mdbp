package com.vgtu.mdbp.repository;

import com.vgtu.mdbp.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByDealerId(String dealerId);
}

