package com.example.sharity.service;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.Insurance;
import com.example.sharity.entity.car.carTypes.ElectricCar;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.errorHandling.NoChangesDateException;
import com.example.sharity.errorHandling.NoChangesStringException;
import com.example.sharity.errorHandling.UniqueException;
import com.example.sharity.errorHandling.customer.EmptyValueException;
import com.example.sharity.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
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


    public void updateCar(String licensePlate, Long customerNumber, double pricePerDay, int batteryCapacity, int kmPerKw, int fastChargingTime, int kmPerLiterFuel) {
        Car car = carRepository.findById(licensePlate).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with licenseplate " + licensePlate + " unknown in database"));

        if (customerNumber != null) {
            if (customerNumber == 0) {
                throw new EmptyValueException("customerNumber can not be 0");
            } else if (customerNumber.equals(car.getCustomerNumber())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, customerNumber + " is the customerNumber matching the car");
            } else {
                car.setCustomerNumber(customerNumber);
                carRepository.save(car);
            }
        }

        if (pricePerDay <= 0.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rent must be higher than zero");
        } else if (pricePerDay > 0.0)  {
            car.setPricePerDay(pricePerDay);
        }

        if (batteryCapacity != 0) {
//                ElectricCar.setBatteryCapacity(batteryCapacity);
//                carRepository.save(car);
        }

        if (kmPerKw != 0) {
//                if (kmPerKw.equals(car.getkmPerKw())) {
//                }
//                car.setKmPerKw(kmPerKw);
//                carRepository.save(car);
        }

        if (fastChargingTime != 0) {

        }

        if (kmPerLiterFuel != 0) {

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
