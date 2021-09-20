package com.example.sharity.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
            Customer Rob = new Customer(
                    "Horst",
                    "van der",
                    "Rob",
                     LocalDate.of(1983, Month.AUGUST, 29),
                    "Moye Keene",
                    "63",
                    "4791BB",
                    "Klundert",
                    "Nederland",
                    "+31639663976",
                    "rpl.vanderhorst@student.avans.nl",
                    "welkom01"
            );
            Customer Bart = new Customer(
                    "Grootoonk",
                    "",
                    "Bart",
                    LocalDate.of(1982, Month.APRIL, 3),
                    "Vogelaar",
                    "23",
                    "4872AH",
                    "Etten-Leur",
                    "Nederland",
                    "+31622578964",
                    "b.grootoonk@student.avans.nl",
                    "welkom02"
            );
            Customer Daniel = new Customer(
                    "Jansen",
                    "",
                    "Daniël",
                    LocalDate.of(1979, Month.JANUARY, 27),
                    "Oosterhoutseweg",
                    "157",
                    "4922XC",
                    "Made",
                    "Nederland",
                    "+31678565123",
                    "d.jansen@student.avans.nl",
                    "welkom03"
            );

            repository.saveAll(
                    List.of(Rob, Bart, Daniel)
            );
        };
    }
}

