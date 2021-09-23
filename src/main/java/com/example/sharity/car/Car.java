package com.example.sharity.car;


import com.example.sharity.Enum.FuelTypeEnum;
import com.example.sharity.Enum.MakeEnum;
import com.example.sharity.customer.Owner;
import com.example.sharity.models.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Table (name = "cars")
@Entity
public class Car extends BaseEntity {

    @Column(unique = true)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column
    private MakeEnum make;

    @Column
    @NotEmpty
    private String model;

    @Enumerated(EnumType.STRING)
    @Column (name = "fuel_type")
    private FuelTypeEnum fuelType;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn (name = "owner_id")
    private Owner owner;





    public Car() {
        super();
    }

    public Car(String licensePlate, MakeEnum make, String model, FuelTypeEnum fuelType, Owner owner) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.fuelType = fuelType;
        this.owner = owner;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MakeEnum getMake() {
        return make;
    }

    public void setMake(MakeEnum make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FuelTypeEnum getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeEnum fuelType) {
        this.fuelType = fuelType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", make=" + make +
                ", model='" + model + '\'' +
                ", fuelType=" + fuelType +
                ", owner=" + owner +
                '}';
    }
}