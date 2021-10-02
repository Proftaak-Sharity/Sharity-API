package com.example.sharity.repository;

import com.example.sharity.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Currency;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer>findCustomerByEmail(String email);

/*
    @Query("SELECT c FROM Customer c WHERE c.customerNumber =?1")
    Optional<Customer>findCustomerByCustomerNumber(Long customerNumber);
*/

}
