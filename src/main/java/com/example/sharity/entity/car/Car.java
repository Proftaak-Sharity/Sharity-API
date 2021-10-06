package com.example.sharity.entity.car;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "car_type", discriminatorType = DiscriminatorType.STRING)
@Entity
public class Car {

    @Id
    @Column(unique = true, length = 20)
    private String licensePlate;

    private Long customerNumber;

    @Enumerated(EnumType.STRING)
    private MakeEnum make;

    private String model;
    private double rent;

    public Car(String licensePlate, MakeEnum make, String model) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
    }

    public Car(String licensePlate, MakeEnum make, String model, Long customerNumber) {
        this.licensePlate = licensePlate;
        this.customerNumber = customerNumber;
        this.make = make;
        this.model = model;
    }

    public Car() {
    }

}