package com.example.sharity.entity.admin;


import com.example.sharity.entity.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="contactForm")
@Table(name = "contactForm")
@Getter
@Setter
public class ContactForm {
    // This is a class to construct a contact form for customers to contact admins

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false )
    private Long formNumber;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn (name = "customer_number")
    private Customer customer_number;

    @Column(nullable = false)
    private LocalDate timeSent;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

}

