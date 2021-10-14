package com.example.sharity.service;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.carTypes.ElectricCar;
import com.example.sharity.entity.car.carTypes.FuelCar;
import com.example.sharity.entity.car.carTypes.HydrogenCar;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.exception.EmptyValueException;
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
        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with licenseplate " + licensePlate + " not known"));
        return car.getPricePerDay();
    }


    public void updateCar(String licensePlate, Long customerNumber, Double pricePerDay, Integer batteryCapacity, Integer kmPerKw, Integer fastChargingTime, Integer kmPerLiterFuel) {
        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with licenseplate " + licensePlate + " unknown in database"));

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

        if (pricePerDay != null) {
            if (pricePerDay <= 0.0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rent must be higher than zero");
            } else if (pricePerDay > 0.0) {
                car.setPricePerDay(pricePerDay);
                carRepository.save(car);
            }
        }
    }

    public void addFuelCar(String licensePlate, Long customerNumber, Make make, String model, Double pricePerDay, FuelType fuelType, Integer sizeFueltank, Integer kmPerLiterFuel) {
        FuelCar fuelCar = new FuelCar();
        fuelCar.setLicensePlate(licensePlate);
        fuelCar.setCustomerNumber(customerNumber);
        fuelCar.setMake(make);
        fuelCar.setModel(model);
        fuelCar.setPricePerDay(pricePerDay);
        fuelCar.setFuelType(fuelType);
        fuelCar.setSizeFueltank(sizeFueltank);
        fuelCar.setKmPerLiterFuel(kmPerLiterFuel);
        carRepository.save(fuelCar);
    }

    public void addElectricCar(String licensePlate, Long customerNumber, Make make, String model, Double pricePerDay, Integer batteryCapacity, Integer kmPerKw, Integer fastChargingTime) {
        ElectricCar electricCar = new ElectricCar();
        electricCar.setLicensePlate(licensePlate);
        electricCar.setCustomerNumber(customerNumber);
        electricCar.setMake(make);
        electricCar.setModel(model);
        electricCar.setPricePerDay(pricePerDay);
        electricCar.setBatteryCapacity(batteryCapacity);
        electricCar.setKmPerKw(kmPerKw);
        electricCar.setFastChargingTime(fastChargingTime);
        carRepository.save(electricCar);
    }

    public void addHydrogenCar(String licensePlate, Long customerNumber, Make make, String model, Double pricePerDay, Integer sizeFueltank, Integer kmPerLiter) {
        HydrogenCar hydrogenCar = new HydrogenCar();
        hydrogenCar.setLicensePlate(licensePlate);
        hydrogenCar.setCustomerNumber(customerNumber);
        hydrogenCar.setMake(make);
        hydrogenCar.setModel(model);
        hydrogenCar.setPricePerDay(pricePerDay);
        hydrogenCar.setSizeFueltank(sizeFueltank);
        hydrogenCar.setKmPerLiter(kmPerLiter);
        carRepository.save(hydrogenCar);
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

//    public void addElectricCar(ElectricCar electricCar) {
//        carRepository.save(electricCar);
//    }
//}
