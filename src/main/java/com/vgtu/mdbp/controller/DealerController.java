package com.vgtu.mdbp.controller;

import com.vgtu.mdbp.model.Dealer;
import com.vgtu.mdbp.model.Product;
import com.vgtu.mdbp.service.DealerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dealers")
@RequiredArgsConstructor
public class DealerController {

    private final DealerService dealerService;

    @PostMapping
    public ResponseEntity<Dealer> createDealer(@RequestBody Dealer dealer) {
        Dealer createdDealer = dealerService.createDealer(dealer);
        return new ResponseEntity<>(createdDealer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Dealer>> getAllDealers() {
        List<Dealer> dealers = dealerService.getAllDealers();
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealerById(@PathVariable String id) {
        Optional<Dealer> dealer = dealerService.getDealerById(id);
        return dealer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Dealer>> getDealersByName(@RequestParam String name) {
        List<Dealer> dealers = dealerService.getDealersByName(name);
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @GetMapping("/search/city")
    public ResponseEntity<List<Dealer>> getDealersByCity(@RequestParam String city) {
        List<Dealer> dealers = dealerService.getDealersByCity(city);
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @GetMapping("/search/active")
    public ResponseEntity<List<Dealer>> getActiveDealers() {
        List<Dealer> dealers = dealerService.getActiveDealers();
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @GetMapping("/search/city-active")
    public ResponseEntity<List<Dealer>> getDealersByCityAndActive(@RequestParam String city, @RequestParam Boolean isActive) {
        List<Dealer> dealers = dealerService.getDealersByCityAndActive(city, isActive);
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dealer> updateDealer(@PathVariable String id, @RequestBody Dealer dealer) {
        Dealer updatedDealer = dealerService.updateDealer(id, dealer);
        if (updatedDealer != null) {
            return new ResponseEntity<>(updatedDealer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealer(@PathVariable String id) {
        if (dealerService.deleteDealer(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllDealers() {
        dealerService.deleteAllDealers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{dealerId}/products")
    public ResponseEntity<List<Product>> getProductsByDealer(@PathVariable String dealerId) {
        List<Product> products = dealerService.getProductsByDealer(dealerId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{dealerId}/stats/product-count")
    public ResponseEntity<Integer> getTotalProductCount(@PathVariable String dealerId) {
        Integer count = dealerService.getTotalProductCount(dealerId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/{dealerId}/stats/inventory-value")
    public ResponseEntity<Double> getTotalInventoryValue(@PathVariable String dealerId) {
        Double value = dealerService.getTotalInventoryValue(dealerId);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}

