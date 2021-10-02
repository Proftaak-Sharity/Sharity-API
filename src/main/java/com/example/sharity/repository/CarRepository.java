package com.example.sharity.repository;

import com.example.sharity.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
   // List<Car> findAllById(Car licensePlate);
}

