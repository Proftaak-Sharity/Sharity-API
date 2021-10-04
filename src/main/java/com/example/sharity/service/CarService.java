package com.example.sharity.service;

import com.example.sharity.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

<<<<<<< Updated upstream
//    private final CarRepository carRepository;
//
//    public CarService carService(CarRepository carRepository){
//        this.carRepository = carRepository;
//    }
=======
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public double getRentfromCar(String licensePlate) {
        Car car = carRepository.findById(licensePlate).orElseThrow(()-> new IllegalStateException("test"));
        return car.getRent();
    }

    public List<Car> getCarById(Car licensePlate){
        return carRepository.findAllById((Iterable<String>) licensePlate);
    }
>>>>>>> Stashed changes
}
