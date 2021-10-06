package com.example.sharity.entity.car;

import com.example.sharity.abstracts.NumberRounder;
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
    private double rent;

    public FuelCar(String licensePlate, MakeEnum make, String model, FuelTypeEnum fuelType, int sizeFueltank, int kmPerLiterFuel, double rent) {
        super(licensePlate, make, model);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiterFuel = kmPerLiterFuel;
        this.fuelType = fuelType;
        this.rent = NumberRounder.roundDouble(rent, 2);
    }

    public FuelCar(String licensePlate, MakeEnum make, String model, FuelTypeEnum fuelType, int sizeFueltank, int kmPerLiterFuel, Long customerNumber, double rent) {
        super(licensePlate, make, model, customerNumber);
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
