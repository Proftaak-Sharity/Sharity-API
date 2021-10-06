package com.example.sharity.entity.car.carTypes;

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
@DiscriminatorValue(value = "HYDROGEN")
public class HydrogenCar extends Car {

    private int sizeFueltank;
    private int kmPerLiter;
    private double rent;


    public HydrogenCar(String licensePlate, Make make, String model, int sizeFueltank, int kmPerLiter, double rent, Insurance insurance) {
        super(licensePlate, make, model, insurance);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiter = kmPerLiter;
        this.rent = rent;
    }

    public HydrogenCar(String licensePlate, Make make, String model, Long customerNumber, int sizeFueltank, int kmPerLiter, double rent, Insurance insurance) {
        super(licensePlate, make, model, customerNumber, insurance);
        this.sizeFueltank = sizeFueltank;
        this.kmPerLiter = kmPerLiter;
        this.rent = rent;
    }

    public HydrogenCar() {
        super();
    }

}
