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
    private double rent;


    public HydrogenCar(String licensePlate, MakeEnum make, String model, int sizeFueltank, int kmPerLiter, double rent) {
        super(licensePlate, make, model);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiter = kmPerLiter;
        this.rent = rent;
    }

    public HydrogenCar(String licensePlate, MakeEnum make, String model, Long customerNumber, int sizeFueltank, int kmPerLiter, double rent) {
        super(licensePlate, make, model, customerNumber);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiter = kmPerLiter;
        this.rent = rent;
    }

    public HydrogenCar() {
        super();
    }

}
