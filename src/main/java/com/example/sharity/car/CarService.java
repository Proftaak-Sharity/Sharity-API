package com.example.sharity.car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public void addNewCar(Car car) {
        Optional<Car> carOptional = carRepository.findCustomerByLicensePlate(car.getLicensePlate());
        if (carOptional.isPresent()) {
            throw new IllegalStateException("Vehicle already known bij Sharity");
        }
        carRepository.save(car);
    }



}
