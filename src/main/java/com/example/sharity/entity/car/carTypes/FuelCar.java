package com.example.sharity.entity.car.carTypes;

import com.example.sharity.abstracts.NumberRounder;
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
    private double rent;

    public FuelCar(String licensePlate, Make make, String model, FuelType fuelType, int sizeFueltank, int kmPerLiterFuel, double rent, Insurance insurance) {
        super(licensePlate, make, model, insurance);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiterFuel = kmPerLiterFuel;
        this.fuelType = fuelType;
        this.rent = NumberRounder.roundDouble(rent, 2);
    }

    public FuelCar(String licensePlate, Make make, String model, FuelType fuelType, int sizeFueltank, int kmPerLiterFuel, Long customerNumber, double rent, Insurance insurance) {
        super(licensePlate, make, model, customerNumber, insurance);
        this.fuelType = fuelType;
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiterFuel = kmPerLiterFuel;
        this.rent = NumberRounder.roundDouble(rent, 2);
    }

    public FuelCar(int kmPerLiterFuel) {
        this.kmPerLiterFuel = kmPerLiterFuel;
    }

    public FuelCar() {
    }
}
