package com.example.sharity.car;


import com.example.sharity.Enum.FuelTypeEnum;
import com.example.sharity.customer.Customer;
import com.example.sharity.models.CarModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Table (name = "cars")
@Entity
public class Car extends CarModel {

    @Enumerated(EnumType.STRING)
    @Column (name = "fuel_type", nullable = false)
    @NotEmpty
    private FuelTypeEnum fuelType;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Car(String licensePlate, String make, String model, FuelTypeEnum fuelType) {
        super(licensePlate, make, model);
        this.fuelType = fuelType;
    }



    public Car() {
        super();
    }

    public FuelTypeEnum getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeEnum fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "fuelType=" + fuelType +
                ", customer=" + customer +
                '}';
    }
}