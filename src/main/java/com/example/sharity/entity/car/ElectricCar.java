package com.example.sharity.entity.car;

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

    private int batteryRange;
    private int kmPerKw;
    private int fastChargingTime;
    private double pricePerDay =  500;

    public ElectricCar(String licensePlate, makeEnum make, String model, int batteryRange, int kmPerKw, int fastChargingTime) {
        super(licensePlate, make, model);
        this.batteryRange = batteryRange;
        this.kmPerKw = kmPerKw;
        this.fastChargingTime = fastChargingTime;
    }

    public ElectricCar(int batteryRange, int kmPerKw, int fastChargingTime) {
        this.batteryRange = batteryRange;
        this.kmPerKw = kmPerKw;
        this.fastChargingTime = fastChargingTime;
    }

    public ElectricCar() {
    }
}

