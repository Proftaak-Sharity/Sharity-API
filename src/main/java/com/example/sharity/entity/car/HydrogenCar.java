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
@DiscriminatorValue(value = "HYDROGEN")
public class HydrogenCar extends Car {

    private int sizeFueltank;
    private int kmPerLiter;
    private double pricePerDay = 400;


    public HydrogenCar(String licensePlate, makeEnum make, String model, int sizeFueltank, int kmPerLiter) {
        super(licensePlate, make, model);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiter = kmPerLiter;
    }


    public HydrogenCar() {
        super();
    }

}
