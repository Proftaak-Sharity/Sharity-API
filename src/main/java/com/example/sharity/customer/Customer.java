package com.example.sharity.customer;

import com.example.sharity.car.Car;
import com.example.sharity.models.PersonModel;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "customers")
public class Customer extends PersonModel {

    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column
    @NotEmpty
    private LocalDate dateOfBirth;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public Set<Car> cars = new HashSet<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bankaccount_id")
    private BankAccount bankaccount;

    public BankAccount getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(BankAccount bankaccount) {
        this.bankaccount = bankaccount;
    }


    public Customer() {
        super();
    }

    public Customer(String lastName, String firstName, LocalDate dateOfBirth, String email, BankAccount bankAccount, Car car) {
        super (lastName, firstName, email);
        this.dateOfBirth = dateOfBirth;
        this.bankaccount = bankAccount;
        this.cars.add(car);
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
    public BankAccount getBankAccount() {
        return bankaccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankaccount = bankAccount;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", city='" + city + '\'' +
                ", bankaccount=" + bankaccount +
                ", cars=" + cars +
                '}';
    }
}

