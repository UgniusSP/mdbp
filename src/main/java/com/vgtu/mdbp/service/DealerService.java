package com.vgtu.mdbp.service;

import com.vgtu.mdbp.model.Dealer;
import com.vgtu.mdbp.repository.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DealerService {
    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public Dealer createDealer(Dealer dealer) {
        if (dealer.getId() == null) {
            dealer.setId(UUID.randomUUID());
        }
        return dealerRepository.save(dealer);
    }

    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    public Optional<Dealer> getDealerById(UUID id) {
        return dealerRepository.findById(id);
    }

    public Dealer updateDealer(UUID id, Dealer dealer) {
        dealer.setId(id);
        return dealerRepository.save(dealer);
    }

    public void deleteDealer(UUID id) {
        dealerRepository.deleteById(id);
    }
}

