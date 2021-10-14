package com.example.sharity.repository;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, String> {

    @Query("SELECT i FROM Insurance i WHERE i.insuranceNumber =?1")
    Optional<Insurance> findInsuranceByInsuranceNumber(String insuranceNumber);

}
