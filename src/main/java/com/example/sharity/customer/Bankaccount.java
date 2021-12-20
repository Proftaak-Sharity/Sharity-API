package com.example.sharity.customer;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Bankaccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String iban;

    @Column(nullable = false)
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

    public Bankaccount(String iban, Long customerNumber, String accountHolder) {
    }
}