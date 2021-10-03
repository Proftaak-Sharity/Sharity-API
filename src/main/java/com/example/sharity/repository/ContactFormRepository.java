package com.example.sharity.repository;

import com.example.sharity.entity.customer.Customer;
import com.example.sharity.entity.admin.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactFormRepository
    extends JpaRepository<ContactForm, Long> {
}



