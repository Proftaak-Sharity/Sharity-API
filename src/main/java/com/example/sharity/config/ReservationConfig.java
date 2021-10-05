package com.example.sharity.config;

import com.example.sharity.entity.car.*;
import com.example.sharity.entity.customer.Bankaccount;
import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.entity.customer.DriversLicense;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.*;
import com.example.sharity.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
public class ReservationConfig {

    @Bean
    CommandLineRunner commandLineRunner (ReservationRepository reservationRepository,
                                         CarRepository carRepository,
                                         CustomerRepository customerRepository,
                                         CarService carService) {
        return args -> {

            Customer Rob = new Customer(
                    "Rob",
                    "van der Horst",
                    "rpl.vanderhorst@student.avans.nl",
                    "welkom01",
                    LocalDate.of(1983, Month.AUGUST, 29),
                    "Hoofdstraat",
                    "12",
                    "Klundert",
                    CountryEnum.NETHERLANDS,
                    new Bankaccount("NL12INGB122365432", "RPL VAN DER HORST"),
                    new FuelCar("KNTK01", makeEnum.Volvo, "XC90", FuelTypeEnum.DIESEL, 52, 12),
                    new DriversLicense("DFAP51056F", CountryEnum.NETHERLANDS, LocalDate.of(2031, Month.APRIL, 22), "../img/driverslicense/HORSR830829.PNG")
            );
            Customer Daniel = new Customer(
                    "Daniël",
                    "Jansen",
                    "d.jansen@yahoo.com",
                    "welkom02",
                    LocalDate.of(1978, Month.MARCH, 20),
                    "Appelweg",
                    "10",
                    "Made",
                    CountryEnum.NETHERLANDS,
                    new Bankaccount("NL78RABO698745632", "D JANSEN"),
                    new HydrogenCar("XX567R", makeEnum.LandRover, "Defender", 75, 10),
                    new DriversLicense("LKSR78191N", CountryEnum.NETHERLANDS, LocalDate.of(2022, Month.AUGUST, 29), "../img/driverslicense/JANSD780320.PNG")
            );
            Customer Bart = new Customer(
                    "Bart",
                    "Grootoonk",
                    "b.grootoonk@planetmail.com",
                    "welkom03",
                    LocalDate.of(1982, Month.NOVEMBER, 9),
                    "Boompjesdijk",
                    "48",
                    "Etten-Leur",
                    CountryEnum.NETHERLANDS,
                    new Bankaccount("NL78RABO126874325", "B GROOTOONK"),
                    new ElectricCar("BG012X", makeEnum.Tesla, "Model Y", 75, 10, 45),
                    new DriversLicense("HTJL65214U", CountryEnum.NETHERLANDS, LocalDate.of(2027, Month.FEBRUARY, 8), "../img/driverslicense/GROOB821109.PNG")
            );
            Customer Lars = new Customer(
                    "Lars",
                    "Hanegraaf",
                    "l.hanegraaf@hotmail.nl",
                    "welkom04",
                    LocalDate.of(1984, Month.APRIL, 18),
                    "Heilig Hartplein",
                    "356-B",
                    "Breda",
                    CountryEnum.NETHERLANDS,
                    new Bankaccount("NL78SNSB098765428", "L HANEGRAAF"),
                    new FuelCar("LH099X", makeEnum.Ford, "Mustang Convertible", FuelTypeEnum.PETROL, 70, 8),
                    new DriversLicense("HSWT82645B", CountryEnum.NETHERLANDS, LocalDate.of(2025, Month.JANUARY, 31), "../img/driverslicense/HANEL840418.PNG")
            );
            Customer Joris = new Customer(
                    "Joris",
                    "Jansen",
                    "jorisjansen@gmail.com",
                    "welkom05",
                    LocalDate.of(1990, Month.DECEMBER, 12),
                    "Tilburgsebaan",
                    "88",
                    "Antwerpen",
                    CountryEnum.BELGIUM,
                    new Bankaccount("NL78RABO985471239", "J JANSEN"),
                    new ElectricCar("JJ001J", makeEnum.Cupra, "Leon", 55, 13, 25),
                    new DriversLicense("JDUT82632P", CountryEnum.BELGIUM, LocalDate.of(2024, Month.FEBRUARY, 29), "../img/driverslicense/JANSJ901212.PNG")
            );
            customerRepository.saveAll(List.of(Rob, Daniel, Bart, Joris, Lars));

            Reservation RobReservation = new Reservation(
                    3L,
                    "KNTK01",
                    carService.getRentFromCar("KNTK01"),
                    LocalDate.of(2021, Month.DECEMBER, 1),
                    LocalDate.of(2021, Month.DECEMBER, 5)
            );
            reservationRepository.save(RobReservation);
        };
    }
}
