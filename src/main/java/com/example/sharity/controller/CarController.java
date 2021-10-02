package com.example.sharity.controller;

import com.example.sharity.entity.car.Car;
import com.example.sharity.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

    @RestController
    @RequestMapping(path = "api/customers")
    public class CarController {

        private final CarService carService;

        @Autowired
        public CarController(CarService carService) {
            this.carService = carService;
        }

//        @GetMapping
//        public Optional<Car> getCarById(Car licensePlate) {
//            return carService.getCarById(licensePlate).stream().findFirst();
//        }


}
