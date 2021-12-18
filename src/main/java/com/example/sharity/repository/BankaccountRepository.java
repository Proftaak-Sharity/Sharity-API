package com.example.sharity.repository;

import com.example.sharity.customer.Bankaccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankaccountRepository extends JpaRepository<Bankaccount, String> {

    @Query("SELECT b.customerNumber FROM Bankaccount b WHERE b.iban = ?1")
    Optional<Bankaccount> checkCustomerByIban(String iban);

    @Query("SELECT b FROM Bankaccount b where b.customerNumber = ?1")
    List<Bankaccount> findAll(Long customerNumber);
}
