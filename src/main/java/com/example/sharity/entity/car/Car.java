package com.example.sharity.entity.car;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "car", discriminatorType = DiscriminatorType.STRING)
@Entity
public class Car {

    @Id
    @Column(unique = true, length = 20)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private makeEnum make;

    private String model;


    public Car(String licensePlate, makeEnum make, String model) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
    }

    public Car() {
    }

}