package com.example.sharity.entity.customer;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
public class Bankaccount {

    @Id
    @Column(length = 20)
    private String iban;

    private String accountHolder;

}