package com.example.sharity.controller;

import com.example.sharity.entity.Payout;
import com.example.sharity.exception.NotFoundException;
import com.example.sharity.repository.PayoutRepository;
import com.example.sharity.service.PayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/payouts")
public class PayoutController {

    private final PayoutService payoutService;
    private final PayoutRepository payoutRepository;

    @Autowired
    public PayoutController(PayoutService payoutService, PayoutRepository payoutRepository) {
        this.payoutService = payoutService;
        this.payoutRepository = payoutRepository;
    }

    @GetMapping
    public List<Payout> findPayouts(){
        return payoutService.findPayouts();
    }

    @GetMapping(path = "{payoutNumber}")
    public Payout getPayout(@PathVariable("payoutNumber") Long payoutNumber) {
        Payout payout = payoutRepository.findById(payoutNumber).orElseThrow(()-> new NotFoundException("Payout", payoutNumber));
        return payoutService.getPayout(payoutNumber);
    }

}
