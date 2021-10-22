package com.example.sharity.exception.car;

import com.example.sharity.car.enums.Make;

public class CreatedException extends RuntimeException {

    public CreatedException(Make make, String model, String licensePlate) {
        super(make + " " + model + " with license plate " + licensePlate + " added succesfully");
    }

    public CreatedException(String key) {
        super(key + " added succesfully");
    }
}
