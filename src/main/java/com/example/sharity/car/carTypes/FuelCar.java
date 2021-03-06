package com.example.sharity.car.carTypes;

import com.example.sharity.service.NumberRounder;
import com.example.sharity.car.Car;
import com.example.sharity.car.enums.FuelType;
import com.example.sharity.car.enums.Make;
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
    private double pricePerDay;
    private double pricePerKm;

    public FuelCar(String licensePlate, Make make, String model, FuelType fuelType, int sizeFueltank, int kmPerLiterFuel, double pricePerDay, double pricePerKm) {
        super(licensePlate, make, model);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiterFuel = kmPerLiterFuel;
        this.fuelType = fuelType;
        this.pricePerDay = NumberRounder.roundDouble(pricePerDay, 2);
        this.pricePerKm = pricePerKm;
    }

    public FuelCar(String licensePlate, Make make, String model, FuelType fuelType, int sizeFueltank, int kmPerLiterFuel, double pricePerDay) {
        super(licensePlate, make, model);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiterFuel = kmPerLiterFuel;
        this.fuelType = fuelType;
        this.pricePerDay = NumberRounder.roundDouble(pricePerDay, 2);
    }

    public FuelCar(String licensePlate, Make make, String model, FuelType fuelType, int sizeFueltank, int kmPerLiterFuel, Long customerNumber, double pricePerDay, double pricePerKm) {
        super(licensePlate, make, model, customerNumber);
        this.fuelType = fuelType;
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiterFuel = kmPerLiterFuel;
        this.pricePerDay = NumberRounder.roundDouble(pricePerDay, 2);
        this.pricePerKm = pricePerKm;
    }

    public FuelCar(int kmPerLiterFuel) {
        this.kmPerLiterFuel = kmPerLiterFuel;
    }

    public FuelCar() {
    }
}
