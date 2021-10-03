package com.example.sharity.entity.reservation;

import com.example.sharity.entity.admin.Payment;
import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.CustomerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerNumber", referencedColumnName = "CustomerNumber")
    public Customer customer;

    @OneToOne(targetEntity = Car.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "licensePlate", referencedColumnName = "licensePlate")
    public Car rentalcar;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int reservationNumber;
    private double rent;
    private LocalDate reservationDate = LocalDate.now();
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(Customer customerNumber, Car licensePlate, LocalDate reservationDate, double rent, LocalDate startDate, LocalDate endDate) {
        // inject the payment methods in reservation
        Payment payment = new Payment();
        CustomerRepository customerRepository ;

        // lets calculate the days we will rent the car
        long days = ChronoUnit.DAYS.between(startDate, endDate);

        customer = customerNumber;

        Car car = licensePlate;
        rent = payment.checkCredit(customer, days, car);
        if (rent >= 0){
            this.customer = customer;
            this.rentalcar = car;
            this.reservationDate = reservationDate;
            this.rent = rent;
            this.startDate = startDate;
            this.endDate = endDate;
        } else {
            System.out.println("We should buy credits first");

        }
    }

    public Reservation() {

    }

//    public double getRent(LocalDate startDate, LocalDate endDate, Car licensePlate) {
//        Stream<LocalDate> rentalDays = startDate.datesUntil(endDate);
//        return rentalDays * pricePerDay;
//    }
}