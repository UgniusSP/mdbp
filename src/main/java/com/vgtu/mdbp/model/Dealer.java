package com.vgtu.mdbp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "dealers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dealer {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private List<UUID> orderIds;
}

