package com.example.sharity.config;

import com.example.sharity.controller.ReservationController;
import com.example.sharity.entity.car.*;
import com.example.sharity.entity.car.carTypes.ElectricCar;
import com.example.sharity.entity.car.carTypes.FuelCar;
import com.example.sharity.entity.car.carTypes.HydrogenCar;
import com.example.sharity.entity.car.enums.Coverage;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.entity.customer.Bankaccount;
import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.entity.customer.DriversLicense;
import com.example.sharity.entity.reservation.PaymentEnum;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.*;
import com.example.sharity.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

@Component
public class PreDataConfig {

    @Bean
    CommandLineRunner commandLineRunner (ReservationRepository reservationRepository,
                                         CarRepository carRepository,
                                         CustomerRepository customerRepository,
                                         CarService carService,
                                         ReservationController reservationController,
                                         BankaccountRepository bankaccountRepository) {
        return args -> {

//          PRE CUSTOMERS
            Customer customerOne = new Customer(
                    "Rob",
                    "van der Horst",
                    "rpl.vanderhorst@student.avans.nl",
                    "welkom01",
                    LocalDate.of(1983, Month.AUGUST, 29),
                    "Hoofdstraat",
                    "12",
                    "4791BG",
                    "Klundert",
                    CountryEnum.NETHERLANDS,
                    "+3165873614",
                    new Bankaccount("NL12INGB122365432", "RPL VAN DER HORST"),
                    new FuelCar("KNTK01", Make.Volvo, "XC90", FuelType.DIESEL, 52, 12, 99.33,
                            new Insurance("AHX0987R", "KNTK01", "AXA UK", Coverage.ALLRISK, LocalDate.of(2022, Month.JANUARY, 18))),
                    new DriversLicense("DFAP51056F", CountryEnum.NETHERLANDS, LocalDate.of(2031, Month.APRIL, 22), "../img/driverslicense/HORSR830829.PNG")
            );
            Customer customerTwo = new Customer(
                    "Daniël",
                    "Jansen",
                    "d.jansen@yahoo.com",
                    "welkom02",
                    LocalDate.of(1978, Month.MARCH, 20),
                    "Appelweg",
                    "10",
                    "4995VB",
                    "Made",
                    CountryEnum.NETHERLANDS,
                    "+31687961423",
                    new Bankaccount("NL78RABO698745632", "D JANSEN"),
                    new HydrogenCar("XX567R", Make.LandRover, "Defender", 75, 10, 245.75,
                            new Insurance("JKP3698741PLX", "XX567R", "Lloyds", Coverage.WAPLUS, LocalDate.of(2022, Month.MAY, 9))),
                    new DriversLicense("LKSR78191N", CountryEnum.NETHERLANDS, LocalDate.of(2022, Month.AUGUST, 29), "../img/driverslicense/JANSD780320.PNG")
            );
            Customer customerThree = new Customer(
                    "Bart",
                    "Grootoonk",
                    "b.grootoonk@planetmail.com",
                    "welkom03",
                    LocalDate.of(1982, Month.NOVEMBER, 9),
                    "Boompjesdijk",
                    "48",
                    "4892SC",
                    "Etten-Leur",
                    CountryEnum.NETHERLANDS,
                    "+31687142694",
                    new Bankaccount("NL78RABO126874325", "B GROOTOONK"),
                    new ElectricCar("BG012X", Make.Tesla, "Model Y", 75, 10, 45, 199.99,
                            new Insurance("JHST718920PO", "BG012X", "MRL Insurance", Coverage.ALLRISK, LocalDate.of(2022, Month.JUNE, 14))),
                    new DriversLicense("HTJL65214U", CountryEnum.NETHERLANDS, LocalDate.of(2027, Month.FEBRUARY, 8), "../img/driverslicense/GROOB821109.PNG")
            );
            Customer customerFour = new Customer(
                    "Lars",
                    "Hanegraaf",
                    "l.hanegraaf@hotmail.nl",
                    "welkom04",
                    LocalDate.of(1984, Month.APRIL, 18),
                    "Heilig Hartplein",
                    "356-B",
                    "4824RA",
                    "Breda",
                    CountryEnum.NETHERLANDS,
                    "+31678163251",
                    new Bankaccount("NL78SNSB098765428", "L HANEGRAAF"),
                    new FuelCar("LH099X", Make.Ford, "Mustang Convertible", FuelType.PETROL, 70, 8, 75.50,
                            new Insurance("HDRA6192PO8", "LH099X", "Sainsbury’s Bank", Coverage.WA, LocalDate.of(2021, Month.NOVEMBER, 30))),
                    new DriversLicense("HSWT82645B", CountryEnum.NETHERLANDS, LocalDate.of(2025, Month.JANUARY, 31), "../img/driverslicense/HANEL840418.PNG")
            );
            Customer customerFive = new Customer(
                    "Joris",
                    "Jansen",
                    "jorisjansen@gmail.com",
                    "welkom05",
                    LocalDate.of(1990, Month.DECEMBER, 12),
                    "Tilburgsebaan",
                    "88",
                    "2100",
                    "Antwerpen",
                    CountryEnum.BELGIUM,
                    "+329875144778514",
                    new Bankaccount("NL78RABO985471239", "J JANSEN"),
                    new ElectricCar("JJ001J", Make.Cupra, "Leon", 55, 13, 25, 121.47,
                            new Insurance("JSHDA012PLK", "JJ001J", "Sheilas’ Wheels", Coverage.ALLRISK, LocalDate.of(2022, Month.FEBRUARY, 8))),
                    new DriversLicense("JDUT82632P", CountryEnum.BELGIUM, LocalDate.of(2024, Month.FEBRUARY, 29), "../img/driverslicense/JANSJ901212.PNG")
            );
            Customer customerSix = new Customer(
                    "Messi",
                    "Leo",
                    "l.messi@voetbal.nl",
                    "welkom65",
                    LocalDate.of(1981, Month.MARCH, 11),
                    "Barcelonaplantsoen",
                    "1",
                    "3425GH",
                    "Utrecht",
                    CountryEnum.NETHERLANDS,
                    "+31625479987",
                    new Bankaccount("NL56SNSB865209873", "L MESSI"),
                    new DriversLicense("HDYT82751L", CountryEnum.NETHERLANDS, LocalDate.of(2029, Month.JUNE, 1), "../img/driverslincense/MESSL810311.PNG")
            );
            customerRepository.saveAll(List.of(customerOne, customerTwo, customerThree, customerFour, customerFive, customerSix));

//          PRE BANKACCOUNTS
            Bankaccount bankaccountOne = new Bankaccount(
                    "NL54SNSB369478521",
                    "L HANEGRAAF EO T VROUWKE",
                    4L
            );
            Bankaccount bankaccountTwo = new Bankaccount(
                    "NL63RABO658746952",
                    "D JANSEN",
                    2L);
            Bankaccount bankaccountThree = new Bankaccount(
                    "NL98ABNA987214669",
                    "RPL VAN DER HORST",
                    1L);
            bankaccountRepository.saveAll(List.of(bankaccountOne, bankaccountTwo, bankaccountThree));

//          PRE CARS
            Car carOne =
                    new FuelCar("RGBB54", Make.MercedesBenz, "ALG318 (AMG)", FuelType.DIESEL, 65, 9, 3L, 299,
                    new Insurance("SDSDA8SD90", "RGBB54", "Sky Insurance", Coverage.ALLRISK, LocalDate.of(2022, Month.JANUARY, 31))

            );
            Car carTwo =
                    new FuelCar("DRGH78", Make.Ferrari, "Testarossa", FuelType.PETROL, 80, 5, 5L, 449,
                    new Insurance("MHSD985PP0", "DRGH78", "Start Rescue", Coverage.ALLRISK, LocalDate.of(2021, Month.DECEMBER, 7))
            );
            Car carThree =
                    new FuelCar("RTP89T", Make.Opel, "Vectra", FuelType.LPG, 55, 10, 1L, 39.90,
                    new Insurance("SDAS67DD990S", "RTP89T", "Tesco Bank", Coverage.WAPLUS, LocalDate.of(2022, Month.MARCH, 28))
            );
            carRepository.saveAll(List.of(carOne, carTwo, carThree));

//          PRE RESERVATIONS
            Reservation reservationOne = new Reservation(
                    3L,
                    "KNTK01",
                    carService.getRentFromCar("KNTK01") * Period.between(LocalDate.of(2021, Month.DECEMBER, 1),
                            LocalDate.of(2021, Month.DECEMBER, 5)).getDays(),
                    LocalDate.of(2021, Month.DECEMBER, 1),
                    LocalDate.of(2021, Month.DECEMBER, 5)
            );
            Reservation reservationTwo = new Reservation(
                    6L,
                    "DRGH78",
                    carService.getRentFromCar("DRGH78") * Period.between(
                            LocalDate.of(2022, Month.JANUARY, 21),
                            LocalDate.of(2022, Month.DECEMBER, 27)).getDays(),
                    LocalDate.of(2022, Month.JANUARY, 21),
                    LocalDate.of(2022, Month.DECEMBER, 27)
            );
            Reservation reservationThree = new Reservation(
                    6L,
                    "DRGH78",
                    carService.getRentFromCar("DRGH78") * Period.between(
                            LocalDate.of(2022, Month.MARCH, 27),
                            LocalDate.of(2022, Month.APRIL, 5)).getDays(),
                    LocalDate.of(2022, Month.MARCH, 27),
                    LocalDate.of(2022, Month.APRIL, 5)
            );
            Reservation reservationFour = new Reservation(
                    1L,
                    "JJ001J",
                    carService.getRentFromCar("JJ001J") * Period.between(
                            LocalDate.of(2021, Month.NOVEMBER, 23),
                            LocalDate.of(2021, Month.DECEMBER, 18)).getDays(),
                    LocalDate.of(2021, Month.NOVEMBER, 23),
                    LocalDate.of(2021, Month.DECEMBER, 18)
            );
            Reservation reservationFive = new Reservation(
                    2L,
                    "LH099X",
                    carService.getRentFromCar("LH099X") * Period.between(
                            LocalDate.of(2022, Month.JULY, 8),
                            LocalDate.of(2022, Month.JULY, 19)).getDays(),
                    LocalDate.of(2022, Month.JULY, 8),
                    LocalDate.of(2022, Month.JULY, 19)
            );
            Reservation reservationSix = new Reservation(
                    2L,
                    "KNTK01",
                    carService.getRentFromCar("KNTK01") * Period.between(
                            LocalDate.of(2022, Month.JULY, 8),
                            LocalDate.of(2022, Month.JULY, 19)).getDays(),
                    LocalDate.of(2022, Month.JULY, 8),
                    LocalDate.of(2022, Month.JULY, 19),
                    PaymentEnum.PAID
            );
            reservationRepository.saveAll(List.of(reservationOne, reservationTwo, reservationThree, reservationFour, reservationFive, reservationSix));

//           PRE UPDATE PAYMENTS
            reservationController.updateReservation(2L, null, null, PaymentEnum.PAID);
            reservationController.updateReservation(3L, null, null, PaymentEnum.PAID);
            reservationController.updateReservation(5L, null, null, PaymentEnum.PAID);

        };
    }
}
