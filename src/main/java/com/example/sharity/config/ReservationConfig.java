package com.example.sharity.config;

import com.example.sharity.entity.car.*;
import com.example.sharity.entity.customer.Bankaccount;
import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.entity.customer.DriversLicense;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
public class ReservationConfig {

    public ReservationConfig(
            ReservationRepository reservationRepository,
            CarRepository carRepository
    ) {

        Customer rob = new Customer(
                "Henk",
                "van der Horst",
                LocalDate.of(1983, Month.AUGUST, 29),
                "Hoofdstraat",
                "12",
                "Klundert",
                CountryEnum.NETHERLANDS,
                new Bankaccount("NL12INGB12231232", "RPL VAN DER HOT", 500.00),
                new FuelCar("KN3201", makeEnum.Volvo, "XC90", FuelTypeEnum.DIESEL, 52, 12),
                new DriversLicense("DFAP51236F", CountryEnum.NETHERLANDS, LocalDate.of(2031, Month.APRIL, 22), "../img/driverslicense/HORSR830829.PNG")

        );




        Car carRob = new FuelCar("K43201", makeEnum.Volvo, "XC90", FuelTypeEnum.DIESEL, 52, 12);

        Reservation Rob = new Reservation(
                rob,
                carRob,
                LocalDate.of(2021, Month.SEPTEMBER, 20),
                carRob.getRent(),
                LocalDate.of(2021, Month.OCTOBER, 20),
                LocalDate.of(2021, Month.SEPTEMBER, 23)

        );

//
        reservationRepository.saveAll(List.of(Rob));
    }
}