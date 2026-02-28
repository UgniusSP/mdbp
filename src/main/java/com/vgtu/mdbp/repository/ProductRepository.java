package com.vgtu.mdbp.repository;

import com.vgtu.mdbp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);
    List<Product> findByDealerId(String dealerId);
    List<Product> findByNameAndDealerId(String name, String dealerId);
}

