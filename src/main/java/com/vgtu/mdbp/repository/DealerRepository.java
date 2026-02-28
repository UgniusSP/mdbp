package com.vgtu.mdbp.repository;

import com.vgtu.mdbp.model.Dealer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DealerRepository extends MongoRepository<Dealer, UUID> {
}

