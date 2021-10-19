package com.example.sharity.controller;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.Insurance;
import com.example.sharity.entity.car.enums.Coverage;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.exception.UpdatedException;
import com.example.sharity.exception.car.*;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.repository.InsuranceRepository;
import com.example.sharity.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/cars")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;
    private final InsuranceRepository insuranceRepository;


    @Autowired
    public CarController(CarService carService, CarRepository carRepository,InsuranceRepository insuranceRepository){

        this.carService = carService;
        this.carRepository = carRepository;
        this.insuranceRepository = insuranceRepository;
    }

    @GetMapping
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping(path = "{licensePlate}")
    public Optional<Car> findCar(
            @PathVariable("licensePlate") String licensePlate) {

        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new NotFoundException("Car ", licensePlate));

        return carService.findCar(licensePlate);
    }

    @PutMapping(path = "{licensePlate}")
    public void updateCar(
            @PathVariable("licensePlate") String licensePlate,
            @RequestParam(required = false) Double pricePerDay) {

        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new NotFoundException("Car", licensePlate));
        carService.updateCar(licensePlate, pricePerDay);
        throw new UpdatedException("Car");
    }

    @DeleteMapping(path = "{licensePlate}")
    public void deleteCar(
            @PathVariable("licensePlate") String licensePlate) {
        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new NotFoundException("Car", licensePlate));
        carService.deleteCar(licensePlate);
        throw new DeletedException("Car");
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
                           throw new BadRequestException("Car");
                       }

        if(fuelType == null && batteryCapacity == null && kmPerLiter == null) {
            throw new BadRequestException();
        } else if (fuelType != null) {
            carService.addFuelCar(licensePlate, customerNumber, make, model, pricePerDay, fuelType, sizeFueltank, kmPerLiterFuel);
        } else if (batteryCapacity != null) {
            carService.addElectricCar(licensePlate, customerNumber, make, model, pricePerDay, batteryCapacity, kmPerKw, fastChargingTime);
        } else {
            carService.addHydrogenCar(licensePlate, customerNumber, make, model, pricePerDay, sizeFueltank, kmPerLiter);
        }
        throw new CreatedException(make, model, licensePlate);

    }


    @PostMapping(path = "/insurance")
    public void addInsurance(
            @RequestParam String licensePlate,
            @RequestParam String insuranceNumber,
            @RequestParam String insuranceCompany,
            @RequestParam Coverage coverage,
            @RequestParam String validUntilString) {
        LocalDate validUntil = LocalDate.parse(validUntilString);

        Optional<Insurance> insuranceOptional = insuranceRepository.findInsuranceByInsuranceNumber(insuranceNumber);
        if (insuranceOptional.isPresent()) {
            throw new BadRequestException("Insurance");
        }

        Insurance insurance = new Insurance(insuranceNumber, licensePlate, insuranceCompany, coverage, validUntil);
        carService.addInsurance(insurance);
        throw new CreatedException("Insurance");
    }

}
