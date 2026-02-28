package com.vgtu.mdbp.service;

import com.vgtu.mdbp.model.Review;
import com.vgtu.mdbp.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(Review review) {
        review.setReviewDate(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(String id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByProduct(String productId) {
        return reviewRepository.findByProductId(productId);
    }

    public List<Review> getReviewsByDealer(String dealerId) {
        return reviewRepository.findByDealerId(dealerId);
    }


    public List<Review> getReviewsByCustomerId(String customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    public List<Review> getVerifiedPurchaseReviews() {
        return reviewRepository.findByIsVerifiedPurchase(true);
    }

    public Review updateReview(String id, Review review) {
        if (reviewRepository.existsById(id)) {
            review.setId(id);
            return reviewRepository.save(review);
        }
        return null;
    }

    public boolean deleteReview(String id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllReviews() {
        reviewRepository.deleteAll();
    }

    public Double getAverageRatingByProduct(String productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    public Double getAverageRatingByDealer(String dealerId) {
        return reviewRepository.findByDealerId(dealerId)
                .stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    public Integer getTotalReviewsByProduct(String productId) {
        return reviewRepository.findByProductId(productId).size();
    }

    public Integer getTotalReviewsByDealer(String dealerId) {
        return reviewRepository.findByDealerId(dealerId).size();
    }

    public List<Review> getHighRatedReviewsByProduct(String productId, Integer minRating) {
        return reviewRepository.findByProductIdAndRating(productId, minRating);
    }

    public List<Review> getHighRatedReviewsByDealer(String dealerId, Integer minRating) {
        return reviewRepository.findByDealerIdAndRating(dealerId, minRating);
    }

    public Review markHelpful(String id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            Review r = review.get();
            r.setHelpfulCount(r.getHelpfulCount() + 1);
            return reviewRepository.save(r);
        }
        return null;
    }
}

