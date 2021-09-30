package com.example.sharity.reservation;

import javax.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.customer.Customer;

    @Getter
    @Setter
    @Entity
    @Table(name = "reservation")
    public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int reservationNumber;

    private double rent;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerNumber", referencedColumnName = "CustomerNumber")
    public Customer customer;

    @OneToOne(targetEntity = Car.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "licensePlate", referencedColumnName = "licensePlate")
    public Car rentalcar;

    public Reservation(Customer customerNumber, Car licensePlate, double rent, LocalDate startDate, LocalDate endDate) {
        this.customer = customerNumber;
        this.rentalcar = licensePlate;
        this.rent = rent;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Reservation(){

    }

        @Override
        public String toString() {
            return "Reservation{" +
                    "reservationNumber=" + reservationNumber +
                    ", rent=" + rent +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", customerNumber=" + customer +
                    ", licensePlate=" + rentalcar +
                    '}';
        }
    }