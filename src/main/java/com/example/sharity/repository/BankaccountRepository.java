package com.example.sharity.repository;

import com.example.sharity.entity.customer.Bankaccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankaccountRepository extends JpaRepository<Bankaccount, String> {
}
