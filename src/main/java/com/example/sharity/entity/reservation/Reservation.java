package com.example.sharity.entity.reservation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    public String licensePlate;

    private Long customerNumber;
    private double rent;
    private LocalDate reservationDate = LocalDate.now();
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment")
    private PaymentEnum paymentEnum = PaymentEnum.OPEN;

    public Reservation(Long customerNumber, String licensePlate, double rent, LocalDate startDate, LocalDate endDate) {
        this.customerNumber = customerNumber;
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.endDate = endDate;
        int days = Period.between(startDate, endDate).getDays();
        this.rent = rent * days;

    }

    public Reservation() {
    }

}