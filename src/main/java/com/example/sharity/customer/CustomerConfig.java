package com.example.sharity.customer;

import com.example.sharity.Enum.FuelTypeEnum;
import com.example.sharity.car.Car;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

import java.util.List;

@SuppressWarnings("ALL")
@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
            Customer Rob = new Customer(
                    "van der Horst",
                    "Rob",
                    LocalDate.of(1983, Month.AUGUST, 29),
                    "rpl.vanderhorst@student.avans.nl",
                    new BankAccount("NL71INGB087123542", "RPL VAN DER HORST"),
                    new Car("XS=XX", "SSSS", "AAAA", FuelTypeEnum.DIESEL)
            );

            Customer Bart = new Customer(
                    "Grootoonk",
                    "Bart",
                    LocalDate.of(1982, Month.APRIL, 3),
                    "b.grootoonk@student.avans.nl",
                    new BankAccount("NL26RABO126024369", "B GROOTOONK"),
                    new Car("XX=XX", "SSSS", "AAAA", FuelTypeEnum.DIESEL)
            );

            Customer Daniel = new Customer(
                    "Jansen",
                    "Daniël",
                     LocalDate.of(1979, Month.JANUARY, 27),
                    "d.jansen@student.avans.nl",
                    new BankAccount("NL12ABNA633256854", "D JANSEN"),
                    new Car("XX=99", "SSSS", "AAAA", FuelTypeEnum.DIESEL)
            );

            repository.saveAll(
                    List.of(Rob, Bart, Daniel)
            );
        };
    }
}

