package com.vgtu.mdbp.service;

import com.vgtu.mdbp.model.Dealer;
import com.vgtu.mdbp.repository.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerService {
    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public Dealer createDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    public Optional<Dealer> getDealerById(String id) {
        return dealerRepository.findById(id);
    }

    public Dealer updateDealer(String id, Dealer dealer) {
        dealer.setId(id);
        return dealerRepository.save(dealer);
    }

    public void deleteDealer(String id) {
        dealerRepository.deleteById(id);
    }
}

