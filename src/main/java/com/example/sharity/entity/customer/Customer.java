package com.example.sharity.entity.customer;

import com.example.sharity.entity.car.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

@Data
@Getter
@Setter
@ToString
@Entity
public class Customer extends PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerNumber;

    private LocalDate dateOfBirth;
    private String address;
    private String houseNumber;
    private String city;

    @Enumerated(EnumType.STRING)
    private CountryEnum country;

    @OneToMany(targetEntity = Car.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerNumber", referencedColumnName = "CustomerNumber")
    private List<Car> cars;

    @OneToMany(targetEntity = Bankaccount.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerNumber", referencedColumnName = "CustomerNumber")
    private List<Bankaccount> bankaccounts;

    @OneToOne(cascade = CascadeType.ALL)
    private DriversLicense driversLicense;


    public Customer(String firstName, String lastName, LocalDate dateOfBirth, String address, String houseNumber, String city, CountryEnum country) {
        super(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
    }

    public Customer(String firstName, String lastName, LocalDate dateOfBirth, String address, String houseNumber, String city, CountryEnum country, Bankaccount bankaccount, DriversLicense driversLicense) {
        super(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
        this.bankaccounts = Collections.singletonList(bankaccount);
        this.driversLicense = driversLicense;
    }

    public Customer(String firstName, String lastName, LocalDate dateOfBirth, String address, String houseNumber, String city, CountryEnum country, Bankaccount bankaccount, Car car) {
        super(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
        this.bankaccounts = Collections.singletonList(bankaccount);
        this.cars = Collections.singletonList(car);
    }
    public Customer(String firstName, String lastName, LocalDate dateOfBirth, String address, String houseNumber, String city, CountryEnum country, Bankaccount bankaccount, Car car, DriversLicense driversLicense) {
        super(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
        this.bankaccounts = Collections.singletonList(bankaccount);
        this.cars = Collections.singletonList(car);
        this.driversLicense = driversLicense;
    }


    public Customer() {
            super();
        }

}




