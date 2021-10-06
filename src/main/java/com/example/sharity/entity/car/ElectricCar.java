package com.example.sharity.entity.car;

import com.example.sharity.abstracts.NumberRounder;
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
    private int fastChargingTime;
    private double rent;

    public ElectricCar(String licensePlate, MakeEnum make, String model, int batteryCapacity, int kmPerKw, int fastChargingTime, double rent) {
        super(licensePlate, make, model);
        this.batteryCapacity = batteryCapacity;
        this.kmPerKw = kmPerKw;
        this.fastChargingTime = fastChargingTime;
        this.rent = NumberRounder.roundDouble(rent, 2);
    }

    public ElectricCar(String licensePlate, MakeEnum make, String model, Long customerNumber, int batteryCapacity, int kmPerKw, int fastChargingTime, double rent) {
        super(licensePlate, make, model, customerNumber);
        this.batteryCapacity = batteryCapacity;
        this.kmPerKw = kmPerKw;
        this.fastChargingTime = fastChargingTime;
        this.rent = NumberRounder.roundDouble(rent, 2);
    }

    public ElectricCar() {
    }
}

