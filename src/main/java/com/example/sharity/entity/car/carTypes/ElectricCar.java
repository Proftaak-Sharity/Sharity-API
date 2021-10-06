package com.example.sharity.entity.car.carTypes;

import com.example.sharity.abstracts.NumberRounder;
import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.entity.car.Insurance;
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

    public ElectricCar(String licensePlate, Make make, String model, int batteryCapacity, int kmPerKw, int fastChargingTime, double rent, Insurance insurance) {
        super(licensePlate, make, model, insurance);
        this.batteryCapacity = batteryCapacity;
        this.kmPerKw = kmPerKw;
        this.fastChargingTime = fastChargingTime;
        this.rent = NumberRounder.roundDouble(rent, 2);
    }

    public ElectricCar(String licensePlate, Make make, String model, Long customerNumber, int batteryCapacity, int kmPerKw, int fastChargingTime, double rent, Insurance insurance) {
        super(licensePlate, make, model, insurance);
        this.batteryCapacity = batteryCapacity;
        this.kmPerKw = kmPerKw;
        this.fastChargingTime = fastChargingTime;
        this.rent = NumberRounder.roundDouble(rent, 2);
    }

    public ElectricCar() {
    }
}

