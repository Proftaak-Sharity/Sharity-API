package com.example.sharity.repository;

import com.example.sharity.customer.Bankaccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankaccountRepository extends JpaRepository<Bankaccount, String> {

    @Query("SELECT b.customerNumber FROM Bankaccount b WHERE b.iban = ?1")
    Optional<Bankaccount> checkCustomerByIban(String iban);


}
