package com.example.sharity.service;

import com.example.sharity.entity.car.Car;
import com.example.sharity.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public double getRentFromCar(String licensePlate) {
        Car car = carRepository.findById(licensePlate).orElseThrow(()-> new IllegalStateException("Car with licenseplate " + licensePlate + " not known"));
        return car.getRent();
    }

    public List<Car> getCarById(Car licensePlate){
        return carRepository.findAllById((Iterable<String>) licensePlate);
    }
}
