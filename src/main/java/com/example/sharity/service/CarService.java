package com.example.sharity.service;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.Insurance;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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


    public void updateCar(String licensePlate, Long customerNumber, Make make, double perDay, Insurance insurance, int batteryCapacity, int kmPerKw, int fastChargingTime, FuelType fuelType, int fueltank, int kmPerLiterFuel, int sizeFueltank, double pricePerDay) {
        Car car = carRepository.findById(licensePlate).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with licenseplate " + licensePlate + " unknown in database"));
        if (pricePerDay <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rent must be higher than zero");
        } else {
            car.setPricePerDay(pricePerDay);
        }

    }

    public void deleteCar(String licensePlate) {
            carRepository.deleteById(licensePlate);
    }

    public Optional<Car> findCar(String licensePlate) {
        Optional<Car> customerOptional = carRepository.findById(licensePlate);

        if (customerOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with  licensePlate " + licensePlate + " is unknown");
        }
        return carRepository.findById(licensePlate);
    }
}
