package com.example.sharity.repository;

import com.example.sharity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("SELECT c FROM Car c WHERE c.licensePlate =?1")
    Optional<Car> findCarByLicensePlate(String licensePlate);
}

