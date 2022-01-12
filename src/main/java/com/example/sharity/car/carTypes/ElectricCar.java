package com.example.sharity.car.carTypes;

import com.example.sharity.service.NumberRounder;
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
@DiscriminatorValue(value = "ELECTRIC")
public class ElectricCar extends Car {

    private int batteryCapacity;
    private int kmPerKw;
    private double pricePerDay;
    private double pricePerKm;

    public ElectricCar(String licensePlate, Make make, String model, int batteryCapacity, int kmPerKw, double pricePerDay, double pricePerKm) {
        super(licensePlate, make, model);
        this.batteryCapacity = batteryCapacity;
        this.kmPerKw = kmPerKw;
        this.pricePerDay = NumberRounder.roundDouble(pricePerDay, 2);
        this.pricePerKm = pricePerKm;
    }

    public ElectricCar(String licensePlate, Make make, String model, Long customerNumber, int batteryCapacity, int kmPerKw, double pricePerDay, double pricePerKm) {
        super(licensePlate, make, model);
        this.batteryCapacity = batteryCapacity;
        this.kmPerKw = kmPerKw;
        this.pricePerDay = NumberRounder.roundDouble(pricePerDay, 2);
        this.pricePerKm = pricePerKm;
    }

    public ElectricCar() {
    }
}

