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









}