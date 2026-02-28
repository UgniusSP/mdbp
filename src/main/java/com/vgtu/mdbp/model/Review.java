package com.vgtu.mdbp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private String id;
    private String productId;
    private String dealerId;
    private String customerId;
    private Integer rating;
    private String title;
    private String comment;
    private LocalDateTime reviewDate;
    private Boolean isVerifiedPurchase;
    private Integer helpfulCount;
}

