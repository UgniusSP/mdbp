package com.vgtu.mdbp.service;

import com.vgtu.mdbp.model.Product;
import com.vgtu.mdbp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> getProductsByDealer(String dealerId) {
        return productRepository.findByDealerId(dealerId);
    }

    public List<Product> getProductsByNameAndDealer(String name, String dealerId) {
        return productRepository.findByNameAndDealerId(name, dealerId);
    }

    public Product updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    public Double getAveragePrice(String dealerId) {
        return productRepository.findByDealerId(dealerId)
                .stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }
}


