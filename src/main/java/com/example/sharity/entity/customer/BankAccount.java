package com.example.sharity.entity.customer;

import com.example.sharity.models.BaseModel;
import lombok.*;

import javax.persistence.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class BankAccount extends BaseModel {

    @Column(unique = true)
    private String iban;

    private String accountHolder;

}