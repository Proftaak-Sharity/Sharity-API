package com.example.sharity.admin;

import com.example.sharity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactFormRepository
    extends JpaRepository<ContactForm, Long> {
}



