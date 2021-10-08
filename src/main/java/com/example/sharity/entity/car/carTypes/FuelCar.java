package com.example.sharity.entity.car.carTypes;

import com.example.sharity.service.NumberRounder;
import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.entity.car.Insurance;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue(value = "FUEL")
public class FuelCar extends Car {


    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private int sizeFueltank;
    private int kmPerLiterFuel;

    // TODO NEW NAME FOR RENT: PRICE PER DAY? SO IT'S MORE CLEAR WHAT IT IS?
    private double pricePerDay;

    public FuelCar(String licensePlate, Make make, String model, FuelType fuelType, int sizeFueltank, int kmPerLiterFuel, double pricePerDay, Insurance insurance) {
        super(licensePlate, make, model, insurance);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiterFuel = kmPerLiterFuel;
        this.fuelType = fuelType;
        this.pricePerDay = NumberRounder.roundDouble(pricePerDay, 2);
    }

    public FuelCar(String licensePlate, Make make, String model, FuelType fuelType, int sizeFueltank, int kmPerLiterFuel, Long customerNumber, double pricePerDay, Insurance insurance) {
        super(licensePlate, make, model, customerNumber, insurance);
        this.fuelType = fuelType;
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiterFuel = kmPerLiterFuel;
        this.pricePerDay = NumberRounder.roundDouble(pricePerDay, 2);
    }

    public FuelCar(int kmPerLiterFuel) {
        this.kmPerLiterFuel = kmPerLiterFuel;
    }

    public FuelCar() {
    }
}
