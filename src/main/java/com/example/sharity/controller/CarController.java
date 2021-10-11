package com.example.sharity.controller;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.Insurance;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping(path = "{licensePlate}")
    public Optional<Car> findCar(
            @PathVariable("licensePlate") String licensePlate) {
        return carService.findCar(licensePlate);
    }

    @PutMapping(path = "{licensePlate}")
    public void updateCar(

    /*
    TODO:
        ask everything we can use as request (for every car type)
        check for cartype and use coreseponding constructor

    */

            @PathVariable("licensePlate") String licensePlate,
            @RequestParam(required = false) Long customerNumber,
            @RequestParam(required = false) Make make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) double pricePerDay,
            @RequestParam(required = false) Insurance insurance,
            @RequestParam(required = false) int batteryCapacity,
            @RequestParam(required = false) int kmPerKw,
            @RequestParam(required = false) int fastChargingTime,
            @RequestParam(required = false) FuelType fuelType,
            @RequestParam(required = false) int sizeFueltank,
            @RequestParam(required = false) int kmPerLiterFuel) throws NoSuchAlgorithmException {
        carService.updateCar(licensePlate, customerNumber, make, pricePerDay, insurance, batteryCapacity, kmPerKw, fastChargingTime, fuelType, sizeFueltank, kmPerLiterFuel, sizeFueltank, kmPerLiterFuel);
    }

    @DeleteMapping(path = "{licensePlate}")
    public void deleteCar(
            @PathVariable("licensePlate") String licensePlate) {
        carService.deleteCar(licensePlate);
    }



}
