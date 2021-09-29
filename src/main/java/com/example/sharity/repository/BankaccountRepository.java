package com.example.sharity.repository;

import com.example.sharity.entity.customer.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankaccountRepository extends JpaRepository<BankAccount, String> {
}
