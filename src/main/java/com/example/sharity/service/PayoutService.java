package com.example.sharity.service;

import com.example.sharity.entity.Payout;
import com.example.sharity.repository.PayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayoutService {

    private final PayoutRepository payoutRepository;

    @Autowired
    public PayoutService(PayoutRepository payoutRepository) {
        this.payoutRepository = payoutRepository;
    }

    public List<Payout> findPayouts() {
        return payoutRepository.findAll();
    }

    public Payout getPayout(Long payoutNumber) {
        return payoutRepository.getById(payoutNumber);
    }

}
