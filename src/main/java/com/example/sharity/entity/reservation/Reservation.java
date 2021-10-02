package com.example.sharity.entity.reservation;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerNumber", referencedColumnName = "CustomerNumber")
    public Customer customer;

    @OneToOne(targetEntity = Car.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "licensePlate", referencedColumnName = "licensePlate")
    public Car rentalcar;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int reservationNumber;
    private double rent;
    private LocalDate reservationDate = LocalDate.now();
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(Customer customerNumber, Car licensePlate, LocalDate reservationDate, double rent, LocalDate startDate, LocalDate endDate) {
        this.customer = (Customer) Collections.singletonList(customerNumber);
        this.rentalcar = (Car) Collections.singletonList(licensePlate);
        this.reservationDate = reservationDate;
        this.rent = rent;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Reservation() {

    }

//    public double getRent(LocalDate startDate, LocalDate endDate, Car licensePlate) {
//        Stream<LocalDate> rentalDays = startDate.datesUntil(endDate);
//        return rentalDays * pricePerDay;
//    }
}