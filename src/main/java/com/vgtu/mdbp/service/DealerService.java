package com.vgtu.mdbp.service;

import com.vgtu.mdbp.model.Dealer;
import com.vgtu.mdbp.model.Product;
import com.vgtu.mdbp.repository.DealerRepository;
import com.vgtu.mdbp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DealerService {

    private final DealerRepository dealerRepository;
    private final ProductRepository productRepository;

    public Dealer createDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    public Optional<Dealer> getDealerById(String id) {
        return dealerRepository.findById(id);
    }

    public List<Dealer> getDealersByName(String name) {
        return dealerRepository.findByName(name);
    }

    public List<Dealer> getDealersByCity(String city) {
        return dealerRepository.findByCity(city);
    }

    public List<Dealer> getActiveDealers() {
        return dealerRepository.findByIsActive(true);
    }

    public List<Dealer> getDealersByCityAndActive(String city, Boolean isActive) {
        return dealerRepository.findByCityAndIsActive(city, isActive);
    }

    public Dealer updateDealer(String id, Dealer dealer) {
        if (dealerRepository.existsById(id)) {
            dealer.setId(id);
            return dealerRepository.save(dealer);
        }
        return null;
    }

    public boolean deleteDealer(String id) {
        if (dealerRepository.existsById(id)) {
            productRepository.deleteAll(productRepository.findByDealerId(id));
            dealerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllDealers() {
        dealerRepository.deleteAll();
    }

    public List<Product> getProductsByDealer(String dealerId) {
        return productRepository.findByDealerId(dealerId);
    }

    public Integer getTotalProductCount(String dealerId) {
        return productRepository.findByDealerId(dealerId).size();
    }

    public Double getTotalInventoryValue(String dealerId) {
        return productRepository.findByDealerId(dealerId)
                .stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
    }
}

