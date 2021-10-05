package com.example.sharity.entity.reservation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Payout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payoutNumber;

    private double rent;

    private Long customerNumber;

    public Payout(double rent, Long customerNumber) {
        this.rent = rent;
        this.customerNumber = customerNumber;
    }

    public Payout() {
    }

}
