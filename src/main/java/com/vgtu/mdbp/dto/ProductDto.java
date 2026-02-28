package com.vgtu.mdbp.dto;

import java.math.BigDecimal;

public class ProductDto {

    private String dealerId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;

    public ProductDto() {}

    public ProductDto(String dealerId, String name, String description,
                      BigDecimal price, Integer stockQuantity) {
        this.dealerId = dealerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}