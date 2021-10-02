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

    private Double credit;


    public Bankaccount(String iban, String accountHolder, Double credit ){
        this.iban = iban;
        this.accountHolder = accountHolder;
        this.credit = credit;
    }

    public Bankaccount() {
    }

}