package com.example.sharity.entity.customer;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class BankAccount {

    @Id
    @Column(length = 20)
    private String iban;

    private String accountHolder;

    public Bankaccount(String iban, String accountHolder) {
        this.iban = iban;
        this.accountHolder = accountHolder;
    }

    public Bankaccount() {
    }
}