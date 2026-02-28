package com.vgtu.mdbp.repository;

import com.vgtu.mdbp.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByProductId(String productId);
    List<Review> findByDealerId(String dealerId);
    List<Review> findByCustomerId(String customerId);
    List<Review> findByProductIdAndRating(String productId, Integer rating);
    List<Review> findByDealerIdAndRating(String dealerId, Integer rating);
    List<Review> findByIsVerifiedPurchase(Boolean isVerifiedPurchase);
}

