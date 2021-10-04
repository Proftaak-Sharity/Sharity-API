package com.example.sharity.entity.car;

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
    private FuelTypeEnum fuelType;

    private int sizeFueltank;
    private int kmPerLiterFuel;
    private double rent = 200;

    public FuelCar(String licensePlate, makeEnum make, String model, FuelTypeEnum fuelType, int sizeFueltank, int kmPerLiterFuel) {
        super(licensePlate, make, model);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiterFuel = kmPerLiterFuel;
        this.fuelType = fuelType;
    }

    public FuelCar(int kmPerLiterFuel) {
        this.kmPerLiterFuel = kmPerLiterFuel;
    }

    public FuelCar() {
    }
}
