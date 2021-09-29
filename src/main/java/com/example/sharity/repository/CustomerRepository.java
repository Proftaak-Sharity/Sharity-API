package com.example.sharity.repository;

import com.example.sharity.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Currency;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.email FROM Customer c WHERE c.email =?1")
    Optional<Customer>findCustomerByEmail(String email);

}
