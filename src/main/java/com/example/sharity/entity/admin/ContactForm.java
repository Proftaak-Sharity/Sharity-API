package com.example.sharity.entity.admin;


import com.example.sharity.entity.customer.BankAccount;
import com.example.sharity.entity.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="contactForm")
@Table(name = "contactForm")
//@SecondaryTable(name = "Customer", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customerNumber"))

public class ContactForm {
    // This is a class to construct a contact form for customers to contact admins

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false )
    private Long formNumber;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn (name = "customer")
    private Customer customer;

    @Column(nullable = false)
    private LocalDate timeSent;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    // Here we create the form
    public ContactForm(Customer customer, LocalDate timeSent, String message) {
        this.customer = customer;
        this.timeSent = timeSent;
        this.message = message;
    }


    public ContactForm() {
    }


    public Long getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(Long formNumber) {
        this.formNumber = formNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalDate timeSent) {
        this.timeSent = timeSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "formNumber=" + formNumber +
                ", customer=" + customer +
                ", timeSent=" + timeSent +
                ", message='" + message + '\'' +
                '}';
    }
}

