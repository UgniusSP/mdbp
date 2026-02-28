package com.vgtu.mdbp.repository;

import com.vgtu.mdbp.model.Dealer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerRepository extends MongoRepository<Dealer, String> {
    List<Dealer> findByName(String name);
    List<Dealer> findByCity(String city);
    List<Dealer> findByIsActive(Boolean isActive);
    List<Dealer> findByCityAndIsActive(String city, Boolean isActive);
}

