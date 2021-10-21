package com.example.sharity.repository;

import com.example.sharity.car.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, String> {

    @Query("SELECT i FROM Insurance i WHERE i.insuranceNumber =?1")
    Optional<Insurance> findInsuranceByInsuranceNumber(String insuranceNumber);

}
