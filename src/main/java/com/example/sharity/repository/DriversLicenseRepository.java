package com.example.sharity.repository;

import com.example.sharity.customer.DriversLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriversLicenseRepository extends JpaRepository<DriversLicense, Long> {

    @Query("SELECT d FROM DriversLicense d WHERE d.customerNumber = ?1")
    Optional<DriversLicense>getDriversLicensesByCustomerNumber(Long customerNumber);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM DriversLicense d where d.licenseNumber = ?1")
    Boolean checkLicense(String licenseNumber);


}
