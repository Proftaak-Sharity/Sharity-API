package com.example.sharity.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CarNotAvailableException extends RuntimeException{



    public CarNotAvailableException(LocalDate startDate, LocalDate endDate) {


        super("Car not available in the period " + startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " - " + endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

    }
}
