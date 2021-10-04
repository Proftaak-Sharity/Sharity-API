package com.example.sharity.entity.reservation;

import com.example.sharity.entity.admin.Payment;
import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.CustomerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int reservationNumber;

    public String licensePlate;

    private Long customerNumber;
    private double rent;
    private LocalDate reservationDate = LocalDate.now();
    private LocalDate startDate;
    private LocalDate endDate;

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