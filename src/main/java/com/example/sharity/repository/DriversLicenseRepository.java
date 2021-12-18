package com.example.sharity.repository;

import com.example.sharity.customer.Bankaccount;
import com.example.sharity.customer.Customer;
import com.example.sharity.customer.DriversLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriversLicenseRepository extends JpaRepository<DriversLicense, Long> {

    @Query("SELECT d FROM DriversLicense d, Customer c WHERE c.customerNumber = ?1 AND (c.driversLicense = d.licenseNumber)")
    Optional<DriversLicense>getDriversLicensesByCustomerNumber(Long customerNumber);


}
