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

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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


    public Customer(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String postalCode, String city, CountryEnum country, String phoneNumber) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String postalCode, String city, CountryEnum country, String phoneNumber, Bankaccount bankaccount, DriversLicense driversLicense) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.bankaccounts = Collections.singletonList(bankaccount);
        this.driversLicense = driversLicense;
        this.phoneNumber = phoneNumber;

    }

    public Customer(String firstName, String lastName,  String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String postalCode, String city, CountryEnum country, String phoneNumber, Bankaccount bankaccount, Car car) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.bankaccounts = Collections.singletonList(bankaccount);
        this.cars = Collections.singletonList(car);
        this.phoneNumber = phoneNumber;

    }
    public Customer(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String postalCode, String city, CountryEnum country, String phoneNumber, Bankaccount bankaccount, Car car, DriversLicense driversLicense) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.bankaccounts = Collections.singletonList(bankaccount);
        this.cars = Collections.singletonList(car);
        this.driversLicense = driversLicense;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
            super();
        }

}




