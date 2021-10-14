package com.example.sharity.controller;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.errorHandling.car.NotFoundException;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/cars")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;


    @Autowired
    public CarController(CarService carService, CarRepository carRepository){

        this.carService = carService;
        this.carRepository = carRepository;
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
            @PathVariable("licensePlate") String licensePlate,
            @RequestParam(required = false) Long customerNumber,
            @RequestParam(required = false) Double pricePerDay,
            @RequestParam(required = false) Integer batteryCapacity,
            @RequestParam(required = false) Integer kmPerKw,
            @RequestParam(required = false) Integer fastChargingTime,
            @RequestParam(required = false) FuelType fuelType,
            @RequestParam(required = false) Integer sizeFueltank,
            @RequestParam(required = false) Double kmPerLiterFuel,
            @RequestParam(required = false) Double kmPerKilo) {

        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new NotFoundException("LicencePlate", licensePlate));
        carService.updateCar(licensePlate, customerNumber, pricePerDay, batteryCapacity, kmPerKw, fastChargingTime, fuelType, sizeFueltank, kmPerLiterFuel, kmPerKilo);
    }

    @DeleteMapping(path = "{licensePlate}")
    public void deleteCar(
            @PathVariable("licensePlate") String licensePlate) {
        carService.deleteCar(licensePlate);
    }

    @PostMapping
    public void addCar(@RequestParam String licensePlate,
                       @RequestParam Long customerNumber,
                       @RequestParam Make make,
                       @RequestParam String model,
                       @RequestParam Double pricePerDay,
                       @RequestParam (required = false) FuelType fuelType,
                       @RequestParam (required = false) Integer sizeFueltank,
                       @RequestParam (required = false) Integer kmPerLiterFuel,
                       @RequestParam (required = false) Integer batteryCapacity,
                       @RequestParam (required = false) Integer kmPerKw,
                       @RequestParam (required = false) Integer fastChargingTime,
                       @RequestParam (required = false) Integer kmPerLiter) {

                       Optional<Car> carOptional = carRepository.findCarByLicensePlate(licensePlate);
                       if (carOptional.isPresent()) {
                           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already in database");
                       }

        if(fuelType == null && batteryCapacity == null && kmPerLiter == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insert fueltype, battery capacity or km per kW to define if it is a fuel-, electric- or hydrogen car");
        } else if (fuelType != null) {
            carService.addFuelCar(licensePlate, customerNumber, make, model, pricePerDay, fuelType, sizeFueltank, kmPerLiterFuel);
            throw new ResponseStatusException(HttpStatus.OK, make + " " + model + " with license plate " + licensePlate + " added to database");
        } else if (batteryCapacity != null) {
            carService.addElectricCar(licensePlate, customerNumber, make, model, pricePerDay, batteryCapacity, kmPerKw, fastChargingTime);
            throw new ResponseStatusException(HttpStatus.OK, make + " " + model + " with license plate " + licensePlate + " added to database");
        } else {
            carService.addHydrogenCar(licensePlate, customerNumber, make, model, pricePerDay, sizeFueltank, kmPerLiter);
            throw new ResponseStatusException(HttpStatus.OK, make + " " + model + " with license plate " + licensePlate + " added to database");
        }
    }

}
