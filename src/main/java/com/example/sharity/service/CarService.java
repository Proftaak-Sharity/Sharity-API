package com.example.sharity.service;

import com.example.sharity.car.Car;
import com.example.sharity.car.TotalCostOwnership;
import com.example.sharity.car.carTypes.ElectricCar;
import com.example.sharity.car.carTypes.FuelCar;
import com.example.sharity.car.carTypes.HydrogenCar;
import com.example.sharity.car.enums.FuelType;
import com.example.sharity.car.enums.Make;
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
    private final TotalCostOwnership totalCostOwnership;

    @Autowired
    public CarService(CarRepository carRepository, TotalCostOwnership totalCostOwnership) {
        this.carRepository = carRepository;
        this.totalCostOwnership = totalCostOwnership;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public List<FuelCar> getFuelCars() { return carRepository.findAllFuelCars();
    }

    public List<ElectricCar> getElectricCars() { return carRepository.findAllElectricCars();
    }

    public List<HydrogenCar> getHydrogenCars() { return carRepository.findAllHydrogenCars();
    }

    public double getRentFromCar(String licensePlate) {
        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with licenseplate " + licensePlate + " not known"));
        return car.getPricePerDay();
    }


    public void updateCar(String licensePlate, Double pricePerDay) {
        Car car = carRepository.findById(licensePlate).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with licenseplate " + licensePlate + " unknown in database"));

        if (pricePerDay != null) {
            if (pricePerDay <= 0.0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PricePerDay must be higher than zero");
            } else if (pricePerDay > 0.0) {
                car.setPricePerDay(pricePerDay);
                carRepository.save(car);
            }
        }
    }

    public FuelCar addFuelCar(String licensePlate, Long customerNumber, Make make, String model, Double pricePerDay, FuelType fuelType, Integer sizeFueltank, Integer kmPerLiterFuel) {
        FuelCar fuelCar = new FuelCar();
        fuelCar.setLicensePlate(licensePlate);
        fuelCar.setCustomerNumber(customerNumber);
        fuelCar.setMake(make);
        fuelCar.setModel(model);
        fuelCar.setPricePerDay(pricePerDay);
        fuelCar.setFuelType(fuelType);
        fuelCar.setSizeFueltank(sizeFueltank);
        fuelCar.setKmPerLiterFuel(kmPerLiterFuel);
        fuelCar.setPricePerKm(totalCostOwnership.TotalCostOwnershipFuel(sizeFueltank, kmPerLiterFuel, fuelType));
        carRepository.save(fuelCar);
        return fuelCar;
    }

    public ElectricCar addElectricCar(String licensePlate, Long customerNumber, Make make, String model, Double pricePerDay, Integer batteryCapacity, Integer kmPerKw) {
        ElectricCar electricCar = new ElectricCar();
        electricCar.setLicensePlate(licensePlate);
        electricCar.setCustomerNumber(customerNumber);
        electricCar.setMake(make);
        electricCar.setModel(model);
        electricCar.setPricePerDay(pricePerDay);
        electricCar.setBatteryCapacity(batteryCapacity);
        electricCar.setKmPerKw(kmPerKw);
        electricCar.setPricePerKm(totalCostOwnership.TotalCostOwnerShipElectric(batteryCapacity, kmPerKw));
        carRepository.save(electricCar);
        return electricCar;
    }

    public HydrogenCar addHydrogenCar(String licensePlate, Long customerNumber, Make make, String model, Double pricePerDay, Integer sizeFueltank, Integer kmPerKilo) {
        HydrogenCar hydrogenCar = new HydrogenCar();
        hydrogenCar.setLicensePlate(licensePlate);
        hydrogenCar.setCustomerNumber(customerNumber);
        hydrogenCar.setMake(make);
        hydrogenCar.setModel(model);
        hydrogenCar.setPricePerDay(pricePerDay);
        hydrogenCar.setSizeFueltank(sizeFueltank);
        hydrogenCar.setKmPerKilo(kmPerKilo);
        hydrogenCar.setPricePerKm(totalCostOwnership.TotalCostOwnerShipHydrogen(sizeFueltank, kmPerKilo));
        carRepository.save(hydrogenCar);
        return hydrogenCar;
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

    public Optional<List<Car>> getCarsFromCustomer(Long customerNumber) {
        return carRepository.getCarsByCustomerNumber(customerNumber);
    }
}

