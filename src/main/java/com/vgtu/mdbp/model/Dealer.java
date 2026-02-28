package com.vgtu.mdbp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "dealers")
public class Dealer {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private List<UUID> orderIds;

    public Dealer() {
    }

    public Dealer(UUID id, String name, String email, String phone, String address, List<UUID> orderIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.orderIds = orderIds;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<UUID> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<UUID> orderIds) {
        this.orderIds = orderIds;
    }
}

