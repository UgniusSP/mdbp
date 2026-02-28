package com.vgtu.mdbp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dealers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dealer {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private Double rating;
    private Boolean isActive;
}

