package com.example.sharity.config;

import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.*;
import com.example.sharity.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class ReservationConfig {


    @Bean
    CommandLineRunner commandLineRunner (ReservationRepository reservationRepository,
                                         CarRepository carRepository,
                                         CustomerRepository customerRepository,
                                         CarService carService) {
        return args -> {

            Reservation Rob = new Reservation(
                    3L,
                    "KNTK01",
                    carService.getRentfromCar("KNTK01"),
                    LocalDate.of(2021, Month.DECEMBER, 1),
                    LocalDate.of(2021, Month.DECEMBER, 5)
            );

            reservationRepository.save(Rob);
        };
    }
}
