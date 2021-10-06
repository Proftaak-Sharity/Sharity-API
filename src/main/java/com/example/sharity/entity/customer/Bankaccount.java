package com.example.sharity.entity.customer;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Bankaccount {

    @Id
    @Column(length = 20)
    private String iban;

    private String accountHolder;

    private Long customerNumber;


    public Bankaccount(String iban, String accountHolder) {
        this.iban = iban;
        this.accountHolder = accountHolder;
    }

    public Bankaccount(String iban, String accountHolder, Long customerNumber) {
        this.iban = iban;
        this.accountHolder = accountHolder;
        this.customerNumber = customerNumber;
    }

    public Bankaccount() {
    }

}