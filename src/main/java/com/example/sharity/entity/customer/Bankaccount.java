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
public class Bankaccount {

    @Id
    @Column(length = 20)
    private String iban;

    private String accountHolder;

}