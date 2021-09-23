package com.example.sharity.admin;


import com.example.sharity.customer.BankAccount;
import com.example.sharity.customer.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@SecondaryTable(name = "Customer", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customerNumber"))

public class ContactForm {
    // This is a class to construct a contact form for customers to contact admins

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long formNumber;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @Column
    private Customer customerNumber;

    @Column
    private LocalDate timeSent;

    @Column
    private String message;

    // Here we create the form
    public ContactForm(Long formNumber, Customer customerNumber, LocalDate timeSent, String message) {
        this.formNumber = formNumber;
        this.customerNumber = customerNumber;
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

//    public String getCustomerNumber() {
//        return customerNumber;
//    }
//
//    public void setLastName(String customerNumber) {
//        this.customerNumber = customerNumber;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public BankAccount getBankAccount() {
//        return bankAccount;
//    }
//
//    public void setBankAccount(BankAccount bankAccount) {
//        this.bankAccount = bankAccount;
//    }
//
//    @Override
//    public String toString() {
//        return "Customer{" +
//                "customerNumber=" + customerNumber +
//                ", lastName='" + lastName + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", dateOfBirth=" + dateOfBirth +
//                ", email='" + email + '\'' +
//                ", bankAccount=" + bankAccount +
//                '}';
//    }
}

