package com.example.sharity.controller;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.Insurance;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.errorHandling.car.NotFoundException;
import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.service.CarService;
import com.example.sharity.service.CustomerService;
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
            @RequestParam(required = false) double pricePerDay,
            @RequestParam(required = false) int batteryCapacity,
            @RequestParam(required = false) int kmPerKw,
            @RequestParam(required = false) int fastChargingTime,
            @RequestParam(required = false) FuelType fuelType,
            @RequestParam(required = false) int sizeFueltank,
            @RequestParam(required = false) int kmPerLiterFuel) throws NoSuchAlgorithmException {

        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new NotFoundException("LicencePlate", licensePlate));
        carService.updateCar(licensePlate, customerNumber, pricePerDay, batteryCapacity, kmPerKw, fastChargingTime, kmPerLiterFuel);
    }

    @DeleteMapping(path = "{licensePlate}")
    public void deleteCar(
            @PathVariable("licensePlate") String licensePlate) {
        carService.deleteCar(licensePlate);
    }



}
