package com.example.sharity.entity.car;


import com.example.sharity.entity.car.enums.Availability;
import com.example.sharity.entity.car.enums.Make;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "car_type", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class Car {

    @Id
    @Column(unique = true, length = 20)
    private String licensePlate;

    private Long customerNumber;

    @Enumerated(EnumType.STRING)
    private Make make;

    private String model;

    // TODO NEW NAME FOR RENT: PRICE PER DAY? SO IT'S MORE CLEAR WHAT IT IS?
    private double pricePerDay;

    // TODO WRITE A METHOD THAT COUNTS IF LOCALDATE.NOW IS BETWEEN LOCALDATES OF RENTPERIOD
    @Enumerated(EnumType.STRING)
    private Availability available = Availability.YES;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Insurance insurance;

    public Car(String licensePlate, Make make, String model, Insurance insurance) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.insurance = insurance;
    }

    public Car(String licensePlate, Make make, String model, Long customerNumber, Insurance insurance) {
        this.licensePlate = licensePlate;
        this.customerNumber = customerNumber;
        this.make = make;
        this.model = model;
        this.insurance = insurance;
    }

    public Car() {
    }

}