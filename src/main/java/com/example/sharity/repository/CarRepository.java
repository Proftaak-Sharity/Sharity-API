package com.example.sharity.repository;

import com.example.sharity.car.Car;
import com.example.sharity.car.carTypes.FuelCar;
import com.example.sharity.car.carTypes.ElectricCar;
import com.example.sharity.car.carTypes.HydrogenCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("SELECT c FROM Car c WHERE c.licensePlate =?1")
    Optional<Car> findCarByLicensePlate(String licensePlate);

    //SELECT DISTINCT license_plate FROM Reservation WHERE ("2022-04-02" BETWEEN start_date AND end_date) OR ("2022-07-19" BETWEEN start_date AND end_date);
    @Query("SELECT c FROM FuelCar c")
    List<FuelCar> findAllFuelCars();

    @Query("SELECT c FROM ElectricCar c")
    List<ElectricCar> findAllElectricCars();

    @Query("SELECT c FROM HydrogenCar c")
    List<HydrogenCar> findAllHydrogenCars();
}

