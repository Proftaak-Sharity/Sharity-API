package com.example.sharity.controller;

import com.example.sharity.payout.Payout;
import com.example.sharity.exception.DeleteNotAllowedException;
import com.example.sharity.exception.InputNotAllowedException;
import com.example.sharity.exception.NotFoundException;
import com.example.sharity.exception.UpdateNotAllowedException;
import com.example.sharity.repository.PayoutRepository;
import com.example.sharity.service.PayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void addPayout() {
        throw new InputNotAllowedException("payout");
    }

    @DeleteMapping
    public void deletePayout() {
        throw new DeleteNotAllowedException("payouts");
    }

    @PutMapping
    public void updatePayout() {
        throw new UpdateNotAllowedException("payouts");
    }



}
