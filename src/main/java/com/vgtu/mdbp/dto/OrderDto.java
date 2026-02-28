package com.vgtu.mdbp.dto;

import com.vgtu.mdbp.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private String dealerId;
    private List<OrderItemDto> items;
    private BigDecimal totalAmount;
    private OrderStatus status;

    public OrderDto() {}

    public OrderDto(String dealerId, List<OrderItemDto> items, BigDecimal totalAmount, OrderStatus status) {
        this.dealerId = dealerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public static class OrderItemDto {
        private String productId;
        private String productName;
        private BigDecimal unitPrice;
        private Integer quantity;

        public OrderItemDto() {}

        public OrderItemDto(String productId, String productName, BigDecimal unitPrice, Integer quantity) {
            this.productId = productId;
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }

        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }

        public BigDecimal getUnitPrice() { return unitPrice; }
        public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    public String getDealerId() { return dealerId; }
    public void setDealerId(String dealerId) { this.dealerId = dealerId; }

    public List<OrderItemDto> getItems() { return items; }
    public void setItems(List<OrderItemDto> items) { this.items = items; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
