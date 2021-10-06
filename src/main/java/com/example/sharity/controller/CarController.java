package com.example.sharity.controller;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.enums.Availability;
import com.example.sharity.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping(path = "{licensePlate}")
    public void updateCar(@PathVariable("licensePlate") String licensePlate,
                          @RequestParam(required = false) double rent,
                          @RequestParam(required = false) Availability available)
    {
        carService.updateCar(licensePlate, rent, available);
    }




}
