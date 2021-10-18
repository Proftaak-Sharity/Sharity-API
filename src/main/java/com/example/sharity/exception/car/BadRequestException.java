package com.example.sharity.exception.car;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Insert fueltype, battery capacity or km per kW to define if it is a fuel-, electric- or hydrogen car");
    }

    public BadRequestException(String key) {
        super(key + " already in database");
    }

}
