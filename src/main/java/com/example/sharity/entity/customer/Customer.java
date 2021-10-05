package com.example.sharity.entity.customer;

import com.example.sharity.entity.car.Car;
import lombok.*;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Customer extends PersonModel{

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
    @ToString.Exclude
    private List<Car> cars;

    @OneToMany(targetEntity = Bankaccount.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerNumber", referencedColumnName = "CustomerNumber")
    @ToString.Exclude
    private List<Bankaccount> bankaccounts;

    @OneToOne(cascade = CascadeType.ALL)
    private DriversLicense driversLicense;


    public Customer(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, CountryEnum country) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
    }

    public Customer(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, CountryEnum country, Bankaccount bankaccount, DriversLicense driversLicense) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
        this.bankaccounts = Collections.singletonList(bankaccount);
        this.driversLicense = driversLicense;
    }

    public Customer(String firstName, String lastName,  String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, CountryEnum country, Bankaccount bankaccount, Car car) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
        this.bankaccounts = Collections.singletonList(bankaccount);
        this.cars = Collections.singletonList(car);
    }
    public Customer(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, CountryEnum country, Bankaccount bankaccount, Car car, DriversLicense driversLicense) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password);
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




