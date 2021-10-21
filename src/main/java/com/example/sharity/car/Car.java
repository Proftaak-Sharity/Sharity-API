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
public class Car {

    @Id
    @Column(unique = true, length = 20)
    private String licensePlate;

    private Long customerNumber;

    @Enumerated(EnumType.STRING)
    private Make make;

    private String model;

    private double pricePerDay;
    private double pricePerKm;

    @OneToOne(cascade = CascadeType.ALL)
//  @JoinColumn(nullable = false)
    private Insurance insurance;

    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "licensePlate", referencedColumnName = "licensePlate")
    @ToString.Exclude
    private List<Reservation> reservations;

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

    public Car(String licensePlate, Make make, String model, Long customerNumber) {
        this.licensePlate = licensePlate;
        this.customerNumber = customerNumber;
        this.make = make;
        this.model = model;
    }

    public Car() {
    }

}