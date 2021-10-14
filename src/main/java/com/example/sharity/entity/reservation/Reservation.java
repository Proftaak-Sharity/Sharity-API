package com.example.sharity.entity.reservation;

import com.example.sharity.service.NumberRounder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;


@Getter
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reservationNumber;

    private String licensePlate;

    private Long customerNumber;
    private double rent;
    private int kmPackage;
    private Double packagePrice;
    private LocalDate reservationDate = LocalDate.now();
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment")
    private PaymentEnum paymentEnum = PaymentEnum.OPEN;

    public Reservation(Long customerNumber, String licensePlate, double rent, int kmPackage, Double packagePrice, LocalDate startDate, LocalDate endDate) {
        this.customerNumber = customerNumber;
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rent = NumberRounder.roundDouble((rent + packagePrice), 2);
        this.kmPackage = kmPackage;
        this.packagePrice = NumberRounder.roundDouble(packagePrice, 2);
    }

    public Reservation(Long customerNumber, String licensePlate, double rent, int kmPackage, Double packagePrice, LocalDate startDate, LocalDate endDate, PaymentEnum paymentEnum) {
        this.customerNumber = customerNumber;
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rent = NumberRounder.roundDouble((rent + packagePrice), 2);
        this.paymentEnum = paymentEnum;
        this.kmPackage = kmPackage;
        this.packagePrice = NumberRounder.roundDouble(packagePrice, 2);
    }
    public Reservation() {
    }

    public Period getPeriod() {
        return Period.between(startDate, endDate);
    }
}