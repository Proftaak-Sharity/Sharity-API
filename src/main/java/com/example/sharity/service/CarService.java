package com.example.sharity.service;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.enums.Availability;
import com.example.sharity.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public double getRentFromCar(String licensePlate) {
        Car car = carRepository.findById(licensePlate).orElseThrow(()-> new IllegalStateException("Car with licenseplate " + licensePlate + " not known"));
        return car.getRent();
    }

    @Transactional
    public void updateCar(String licensePlate, double rent, Availability available) {
        Car car = carRepository.findById(licensePlate).orElseThrow(()-> new IllegalStateException("Car with licenseplate " + licensePlate + " unknown in database"));

        if (rent <= 0) {
            throw new IllegalStateException("Rent must be higher than zero");
        } else {
            car.setRent(rent);
        }

        if (car.getAvailable() == available) {
            if (available == Availability.YES) {
                throw new IllegalStateException("Car already available");
            } else if (available == Availability.NO) {
                throw new IllegalStateException("Car already inavailable");
            }
        } else {
            car.setAvailable(available);
        }

    }
}
