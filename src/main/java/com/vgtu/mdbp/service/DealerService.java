package com.vgtu.mdbp.service;

import com.vgtu.mdbp.dto.DealerDto;
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

    public Dealer createDealer(DealerDto dealer) {
        Dealer newDealer = new Dealer(
                dealer.getName(),
                dealer.getEmail(),
                dealer.getPhone(),
                dealer.getAddress()
        );
        return dealerRepository.save(newDealer);
    }

    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    public Optional<Dealer> getDealerById(String id) {
        return dealerRepository.findById(id);
    }

    public Dealer updateDealer(String id, DealerDto dealerDto) {
        Dealer dealer = dealerRepository.getDealerById(id);
        dealer.setName(dealerDto.getName());
        dealer.setEmail(dealerDto.getEmail());
        dealer.setPhone(dealerDto.getPhone());
        dealer.setAddress(dealerDto.getAddress());
        return dealerRepository.save(dealer);
    }

    public void deleteDealer(String id) {
        dealerRepository.deleteById(id);
    }
}

