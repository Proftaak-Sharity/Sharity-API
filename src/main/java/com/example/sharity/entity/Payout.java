package com.example.sharity.entity;

import com.example.sharity.service.NumberRounder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class Payout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payoutNumber;

//    @Column(name = "payoutAmount")
    private double payoutAmount;
    private double tax;

    private Long customerNumber;

    private Long reservationNumber;

    public Payout(Long reservationNumber, double payoutAmount, double tax, Long customerNumber) {
        this.reservationNumber = reservationNumber;
        this.tax = NumberRounder.roundDouble(tax, 2);
        this.payoutAmount = NumberRounder.roundDouble(payoutAmount, 2);
        this.customerNumber = customerNumber;
    }

    public Payout() {
    }
}
