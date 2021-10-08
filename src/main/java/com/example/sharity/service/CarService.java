package com.example.sharity.service;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.enums.Availability;
import com.example.sharity.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        Car car = carRepository.findById(licensePlate).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with licenseplate " + licensePlate + " not known"));
        return car.getPricePerDay();
    }


    public void updateCar(String licensePlate, double pricePerDay, Availability available) {
        Car car = carRepository.findById(licensePlate).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with licenseplate " + licensePlate + " unknown in database"));

        if (pricePerDay <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rent must be higher than zero");
        } else {
            car.setPricePerDay(pricePerDay);
        }

        if (car.getAvailable() == available) {
            if (available == Availability.YES) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already available");
            } else if (available == Availability.NO) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already inavailable");
            }
        } else {
            car.setAvailable(available);
        }

    }
}
