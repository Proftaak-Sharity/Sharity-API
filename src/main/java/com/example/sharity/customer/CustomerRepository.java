package com.example.sharity.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {


    //    SELECT * FROM customer WHERE email = :email
    @Query
    Optional<Customer> findCustomerByEmail(String email);
}
