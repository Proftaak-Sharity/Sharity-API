package com.example.sharity.customer;

import com.example.sharity.car.Car;
import com.example.sharity.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer extends PersonModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_number")
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountryEnum country;

    @OneToMany(targetEntity = Car.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_number", referencedColumnName = "customer_number")
    @ToString.Exclude
    private List<Car> cars;

    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_number", referencedColumnName = "customer_number")
    @ToString.Exclude
    private List<Reservation> reservations;


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

    public Customer(String firstName, String lastName,  String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String postalCode, String city, CountryEnum country, String phoneNumber, Car car) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.cars = Collections.singletonList(car);
        this.phoneNumber = phoneNumber;

    }

    public Customer() {
            super();
        }

}




