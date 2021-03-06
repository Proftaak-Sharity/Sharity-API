package com.example.sharity.controller;

import com.example.sharity.car.Car;
import com.example.sharity.car.CarImage;
import com.example.sharity.car.carTypes.ElectricCar;
import com.example.sharity.car.carTypes.FuelCar;
import com.example.sharity.car.carTypes.HydrogenCar;
import com.example.sharity.car.enums.FuelType;
import com.example.sharity.car.enums.Make;
import com.example.sharity.exception.car.*;
import com.example.sharity.repository.CarImageRepository;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/cars")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;
    private final CarImageRepository carImageRepository;


    @Autowired
    public CarController(CarService carService, CarRepository carRepository, CarImageRepository carImageRepository){

        this.carService = carService;
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
    }

    @GetMapping
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping(path = "{licensePlate}")
    public Optional<Car> getCar(@PathVariable("licensePlate") String licensePlate) {

        return carService.getCar(licensePlate);
    }

    @GetMapping(path = "/fuelcars")
    public List<FuelCar> getFuelCars() { return carService.getFuelCars();}

    @GetMapping(path = "electriccars")
    public List<ElectricCar> getElectricCars() { return carService.getElectricCars();}

    @GetMapping(path = "hydrogencars")
    public List<HydrogenCar> getHydrogenCars() { return carService.getHydrogenCars();}

    @GetMapping(path = "/customer/{customerNumber}")
    public Optional<List<Car>> getCarsFromCustomer(@PathVariable ("customerNumber") Long customerNumber) {

        return carService.getCarsFromCustomer(customerNumber);
    }

    @PutMapping(path = "{licensePlate}")
    public void updateCar(@PathVariable("licensePlate") String licensePlate,
                          @RequestParam(required = false) Double pricePerDay) {

        carService.updateCar(licensePlate, pricePerDay);
    }

    @DeleteMapping(path = "{licensePlate}")
    public void deleteCar(@PathVariable("licensePlate") String licensePlate) {

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
                       @RequestParam (required = false) Integer kmPerLiter,
                       @RequestParam (required = false) Integer batteryCapacity,
                       @RequestParam (required = false) Integer kmPerKw,
                       @RequestParam (required = false) Integer kmPerKilo) {

                       Optional<Car> carOptional = carRepository.findCarByLicensePlate(licensePlate);
                       if (carOptional.isPresent()) {
                           throw new BadRequestException("Car");
                       }

        if(fuelType == null && batteryCapacity == null && kmPerKilo == null) {
            throw new BadRequestException();
        } else if (fuelType != null) {
            carService.addFuelCar(licensePlate, customerNumber, make, model, pricePerDay, fuelType, sizeFueltank, kmPerLiter);
        } else if (batteryCapacity != null) {
            carService.addElectricCar(licensePlate, customerNumber, make, model, pricePerDay, batteryCapacity, kmPerKw);
        } else {
            carService.addHydrogenCar(licensePlate, customerNumber, make, model, pricePerDay, sizeFueltank, kmPerKilo);
        }
        throw new CreatedException(make, model, licensePlate);
    }

    @PostMapping(path = "image")
    public void addCarImage(@RequestParam String licensePlate,
                            @RequestParam String image) {

        CarImage carImage = new CarImage();
        carImage.setLicensePlate(licensePlate);
        carImage.setImage(image);
        carImageRepository.save(carImage);
    }

    @GetMapping(path = "image/{licensePlate}")
    public CarImage getCarImage(@PathVariable String licensePlate) {
        return carImageRepository.findById(licensePlate).orElseThrow(()-> new com.example.sharity.exception.NotFoundException("Car", licensePlate));
    }
}
