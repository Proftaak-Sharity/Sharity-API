package com.example.sharity.car.carTypes;

import com.example.sharity.car.Car;
import com.example.sharity.car.enums.Make;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue(value = "HYDROGEN")
public class HydrogenCar extends Car {

    private int sizeFueltank;
    private int kmPerKilo;
    private double pricePerDay;
    private double pricePerKm;


    public HydrogenCar(String licensePlate, Make make, String model, int sizeFueltank, int kmPerKilo, double pricePerDay, double pricePerKm) {
        super(licensePlate, make, model);
        this.sizeFueltank = sizeFueltank;
        this.kmPerKilo = kmPerKilo;
        this.pricePerDay = pricePerDay;
        this.pricePerKm = pricePerKm;
    }

    public HydrogenCar(String licensePlate, Make make, String model, Long customerNumber, int sizeFueltank, int kmPerKilo, double pricePerDay, double pricePerKm) {
        super(licensePlate, make, model, customerNumber);
        this.sizeFueltank = sizeFueltank;
        this.kmPerKilo = kmPerKilo;
        this.pricePerDay = pricePerDay;
        this.pricePerKm = pricePerKm;
    }

    public HydrogenCar() {
        super();
    }

}
