package com.vgtu.mdbp.controller;

import com.vgtu.mdbp.dto.DealerDto;
import com.vgtu.mdbp.model.Dealer;
import com.vgtu.mdbp.service.DealerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealers")
public class DealerController {
    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping
    public ResponseEntity<Dealer> createDealer(@RequestBody DealerDto dealer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dealerService.createDealer(dealer));
    }

    @GetMapping
    public ResponseEntity<List<Dealer>> getAllDealers() {
        return ResponseEntity.ok(dealerService.getAllDealers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealerById(@PathVariable String id) {
        return dealerService.getDealerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dealer> updateDealer(@PathVariable String id, @RequestBody DealerDto dealer) {
        return ResponseEntity.ok(dealerService.updateDealer(id, dealer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealer(@PathVariable String id) {
        dealerService.deleteDealer(id);
        return ResponseEntity.noContent().build();
    }
}

