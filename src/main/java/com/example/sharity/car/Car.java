package com.example.sharity.car;



import com.example.sharity.car.enums.Make;
import com.example.sharity.reservation.Reservation;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "car_type", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class Car {

    @Id
    @Column(unique = true, length = 20, name = "license_plate")
    private String licensePlate;

    @Column(name = "customer_number")
    private Long customerNumber;

    @Enumerated(EnumType.STRING)
    private Make make;

    private String model;

    private double pricePerDay;
    private double pricePerKm;


    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "license_plate", referencedColumnName = "license_plate")
    @ToString.Exclude
    private List<Reservation> reservations;

    public Car(String licensePlate, Make make, String model) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
    }

    public Car(String licensePlate, Make make, String model, Long customerNumber) {
        this.licensePlate = licensePlate;
        this.customerNumber = customerNumber;
        this.make = make;
        this.model = model;
    }

    public Car() {
    }

}