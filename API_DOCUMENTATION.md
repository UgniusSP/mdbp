# E-Commerce System API Documentation

## Base URL
http://localhost:8080/api

---

## Products API

### 1. Create Product
**POST** `/products`

**Request Body:**
```json
{
  "name": "Test Product",
  "description": "Test Description",
  "price": 99.99,
  "stockQuantity": 100
}
```

### 2. Get All Products
**GET** `/products`

### 3. Get Product by ID
**GET** `/products/{id}`

Example: `GET /products/a1b2c3d4-e5f6-4a7b-8c9d-0e1f2a3b4c5d`

### 4. Update Product
**PUT** `/products/{id}`

**Request Body:**
```json
{
  "name": "Updated Product",
  "description": "Updated Description",
  "price": 149.99,
  "stockQuantity": 150
}
```

### 5. Delete Product
**DELETE** `/products/{id}`

---

## Dealers API

### 1. Create Dealer
**POST** `/dealers`

**Request Body:**
```json
{
  "name": "New Dealer",
  "email": "dealer@example.com",
  "phone": "+1-555-9999",
  "address": "123 New Street",
  "orderIds": []
}
```

### 2. Get All Dealers
**GET** `/dealers`

### 3. Get Dealer by ID
**GET** `/dealers/{id}`

Example: `GET /dealers/11111111-1111-1111-1111-111111111111`

### 4. Update Dealer
**PUT** `/dealers/{id}`

**Request Body:**
```json
{
  "name": "Updated Dealer",
  "email": "updated@example.com",
  "phone": "+1-555-8888",
  "address": "456 Updated Street",
  "orderIds": ["00000001-0000-0000-0000-000000000001"]
}
```

### 5. Delete Dealer
**DELETE** `/dealers/{id}`

---

## Orders API

### 1. Create Order
**POST** `/orders`

**Request Body:**
```json
{
  "dealerId": "11111111-1111-1111-1111-111111111111",
  "items": [
    {
      "productId": "a1b2c3d4-e5f6-4a7b-8c9d-0e1f2a3b4c5d",
      "productName": "Laptop Dell XPS 15",
      "quantity": 1,
      "price": 1299.99
    }
  ],
  "totalAmount": 1299.99,
  "orderDate": "2026-02-28T12:00:00",
  "status": "PENDING"
}
```

### 2. Get All Orders
**GET** `/orders`

### 3. Get Order by ID
**GET** `/orders/{id}`

Example: `GET /orders/00000001-0000-0000-0000-000000000001`

### 4. Get Orders by Dealer ID
**GET** `/orders/dealer/{dealerId}`

Example: `GET /orders/dealer/11111111-1111-1111-1111-111111111111`

### 5. Update Order
**PUT** `/orders/{id}`

**Request Body:**
```json
{
  "dealerId": "11111111-1111-1111-1111-111111111111",
  "items": [
    {
      "productId": "a1b2c3d4-e5f6-4a7b-8c9d-0e1f2a3b4c5d",
      "productName": "Laptop Dell XPS 15",
      "quantity": 2,
      "price": 1299.99
    }
  ],
  "totalAmount": 2599.98,
  "orderDate": "2026-02-28T12:00:00",
  "status": "COMPLETED"
}
```

### 6. Delete Order
**DELETE** `/orders/{id}`

---

## Mock Data Overview

### Products (8 items)
- Laptop Dell XPS 15 - $1,299.99
- iPhone 15 Pro - $999.99
- Samsung 4K TV 55" - $799.99
- Sony WH-1000XM5 - $399.99
- MacBook Air M3 - $1,199.99
- iPad Pro 12.9" - $1,099.99
- Logitech MX Master 3S - $99.99
- Mechanical Keyboard RGB - $149.99

### Dealers (5 items)
- Tech Solutions Inc
- Electronics Hub
- Digital World
- Smart Devices Co
- Global Electronics

### Orders (6 items)
- 2 orders for Tech Solutions Inc
- 1 order each for other dealers

All entities use UUID as their primary identifiers.

