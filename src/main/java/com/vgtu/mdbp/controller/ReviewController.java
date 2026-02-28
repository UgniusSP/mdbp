package com.vgtu.mdbp.controller;

import com.vgtu.mdbp.model.Review;
import com.vgtu.mdbp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(@PathVariable String productId) {
        List<Review> reviews = reviewService.getReviewsByProduct(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<Review>> getReviewsByDealer(@PathVariable String dealerId) {
        List<Review> reviews = reviewService.getReviewsByDealer(dealerId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getReviewsByCustomerId(@PathVariable String customerId) {
        List<Review> reviews = reviewService.getReviewsByCustomerId(customerId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/search/verified")
    public ResponseEntity<List<Review>> getVerifiedPurchaseReviews() {
        List<Review> reviews = reviewService.getVerifiedPurchaseReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable String id, @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(id, review);
        if (updatedReview != null) {
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        if (reviewService.deleteReview(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllReviews() {
        reviewService.deleteAllReviews();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/product/{productId}/rating")
    public ResponseEntity<Double> getAverageRatingByProduct(@PathVariable String productId) {
        Double avgRating = reviewService.getAverageRatingByProduct(productId);
        return new ResponseEntity<>(avgRating, HttpStatus.OK);
    }

    @GetMapping("/dealer/{dealerId}/rating")
    public ResponseEntity<Double> getAverageRatingByDealer(@PathVariable String dealerId) {
        Double avgRating = reviewService.getAverageRatingByDealer(dealerId);
        return new ResponseEntity<>(avgRating, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}/count")
    public ResponseEntity<Integer> getTotalReviewsByProduct(@PathVariable String productId) {
        Integer count = reviewService.getTotalReviewsByProduct(productId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/dealer/{dealerId}/count")
    public ResponseEntity<Integer> getTotalReviewsByDealer(@PathVariable String dealerId) {
        Integer count = reviewService.getTotalReviewsByDealer(dealerId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}/high-rated")
    public ResponseEntity<List<Review>> getHighRatedReviewsByProduct(
            @PathVariable String productId,
            @RequestParam Integer minRating) {
        List<Review> reviews = reviewService.getHighRatedReviewsByProduct(productId, minRating);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/dealer/{dealerId}/high-rated")
    public ResponseEntity<List<Review>> getHighRatedReviewsByDealer(
            @PathVariable String dealerId,
            @RequestParam Integer minRating) {
        List<Review> reviews = reviewService.getHighRatedReviewsByDealer(dealerId, minRating);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PutMapping("/{id}/mark-helpful")
    public ResponseEntity<Review> markHelpful(@PathVariable String id) {
        Review review = reviewService.markHelpful(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

