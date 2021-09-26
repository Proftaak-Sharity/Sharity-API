package com.example.sharity.car;

import com.example.sharity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository
        extends JpaRepository<Car, Long>{

    Optional<Car> findByLicensePlate(Car car);

    @Query
    Optional<Car> findCustomerByLicensePlate(String licensePlate);
}

