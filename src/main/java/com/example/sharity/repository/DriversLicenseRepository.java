package com.example.sharity.repository;

import com.example.sharity.entity.customer.DriversLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversLicenseRepository extends JpaRepository<DriversLicense, String> {
}
