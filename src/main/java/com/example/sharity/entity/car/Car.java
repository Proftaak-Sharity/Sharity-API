package com.example.sharity.entity.car;


import com.example.sharity.entity.car.Enums.Availability;
import com.example.sharity.entity.car.Enums.Make;
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
    private Make make;

    private String model;
    private double rent;

    @Enumerated(EnumType.STRING)
    private Availability available = Availability.YES;

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