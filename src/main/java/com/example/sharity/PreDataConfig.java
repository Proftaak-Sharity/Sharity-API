package com.example.sharity;

import com.example.sharity.controller.CarController;
import com.example.sharity.controller.CustomerController;
import com.example.sharity.controller.ReservationController;
import com.example.sharity.car.*;
import com.example.sharity.car.carTypes.ElectricCar;
import com.example.sharity.car.carTypes.FuelCar;
import com.example.sharity.car.carTypes.HydrogenCar;
import com.example.sharity.car.enums.FuelType;
import com.example.sharity.car.enums.Make;
import com.example.sharity.customer.*;
import com.example.sharity.reservation.PaymentEnum;
import com.example.sharity.reservation.Reservation;
import com.example.sharity.repository.*;
import com.example.sharity.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Component
public class PreDataConfig {

    @Bean
    CommandLineRunner commandLineRunner (ReservationRepository reservationRepository,
                                         CarRepository carRepository,
                                         CustomerRepository customerRepository,
                                         CarService carService,
                                         ReservationController reservationController,
                                         PasswordGenerator passwordGenerator,
                                         TotalCostOwnership totalCostOwnership,
                                         DriversLicenseRepository driversLicenseRepository,
                                         CarController carController,
                                         CustomerController customerController) {

        Optional<Customer> customer = customerRepository.findById(1L);

        if (customer.isPresent()) {
            return args -> {
            };
        } else {
            return args -> {

//          PRE CUSTOMERS
                Customer customerOne = new Customer(
                        "Rob",
                        "van der Horst",
                        "123",
                        "123",
                        LocalDate.of(1983, Month.AUGUST, 29),
                        "Van Heuven Goedhartlaan",
                        "59",
                        "2132WZ",
                        "Hoofddorp",
                        CountryEnum.NETHERLANDS,
                        "+31645873614",
                        new FuelCar("ZK658B", Make.Volvo, "XC90", FuelType.DIESEL, 52, 12, 60, totalCostOwnership.TotalCostOwnershipFuel(52, 12, FuelType.DIESEL)));
                Customer customerTwo = new Customer(
                        "Dani??l",
                        "Jansen",
                        "d.jansen@yahoo.com",
                        "welkom01",
                        LocalDate.of(1978, Month.MARCH, 20),
                        "De Horst",
                        "132",
                        "9254AS",
                        "Hurdegaryp",
                        CountryEnum.NETHERLANDS,
                        "+31632561423",
                        new FuelCar("VLT74G", Make.LandRover, "Defender", FuelType.DIESEL, 90, 8, 75, totalCostOwnership.TotalCostOwnershipFuel(90, 8, FuelType.DIESEL)));
                Customer customerThree = new Customer(
                        "Bart",
                        "Grootoonk",
                        "b.grootoonk@planetmail.com",
                        "welkom01",
                        LocalDate.of(1982, Month.NOVEMBER, 9),
                        "Pastoorsdijk",
                        "68",
                        "6587AP",
                        "Middelaar",
                        CountryEnum.NETHERLANDS,
                        "+31687142694",
                        new ElectricCar("ZL948B", Make.Tesla, "Model 3", 20, 10, 80, totalCostOwnership.TotalCostOwnerShipElectric(20, 10)));
                Customer customerFour = new Customer(
                        "Lars",
                        "Hanegraaf",
                        "l.hanegraaf@hotmail.nl",
                        "welkom01",
                        LocalDate.of(1984, Month.APRIL, 18),
                        "Route De Thionville",
                        "8",
                        "L-2610",
                        "Luxembourg",
                        CountryEnum.LUXEMBOURG,
                        "+35226480164",
                        new FuelCar("PM0373", Make.Ford, "Mustang Convertible", FuelType.PETROL, 70, 8, 150, totalCostOwnership.TotalCostOwnershipFuel(70, 8, FuelType.PETROL)));
                Customer customerFive = new Customer(
                        "Joris",
                        "Jansen",
                        "jorisjansen@gmail.com",
                        "welkom01",
                        LocalDate.of(1990, Month.DECEMBER, 12),
                        "Rue de Birmingham",
                        "484",
                        "7912",
                        "Dergneau",
                        CountryEnum.BELGIUM,
                        "+320487456754",
                        new ElectricCar("K536DS", Make.Cupra, "Leon", 23, 13,  79, totalCostOwnership.TotalCostOwnerShipElectric(23, 13)));
                Customer customerSix = new Customer(
                        "Leo",
                        "Messi",
                        "l.messi@voetbal.nl",
                        "welkom01",
                        LocalDate.of(1981, Month.MARCH, 11),
                        "Nicolaas Beetsstraat",
                        "194",
                        "4735AS",
                        "Zegge",
                        CountryEnum.NETHERLANDS,
                        "+31625479987"
                );
                customerRepository.saveAll(List.of(customerOne, customerTwo, customerThree, customerFour, customerFive, customerSix));


//          PRE LICENSES
                DriversLicense driversLicenseOne = new DriversLicense("HDYT82751L", CountryEnum.NETHERLANDS, LocalDate.of(2029, Month.JUNE, 1), 6L);
                DriversLicense driversLicenseTwo = new DriversLicense("JDUT82632P", CountryEnum.BELGIUM, LocalDate.of(2024, Month.FEBRUARY, 29), 5L);
                DriversLicense driversLicenseThree = new DriversLicense("HSWT82645B", CountryEnum.NETHERLANDS, LocalDate.of(2025, Month.JANUARY, 31), 1L);
                DriversLicense driversLicenseFour = new DriversLicense("DFAP51056F", CountryEnum.NETHERLANDS, LocalDate.of(2031, Month.APRIL, 22), 2L);
                DriversLicense driversLicenseFive = new DriversLicense("LKSR78191N", CountryEnum.NETHERLANDS, LocalDate.of(2022, Month.AUGUST, 29), 3L);
                DriversLicense driversLicenseSix = new DriversLicense("HTJL65214U", CountryEnum.NETHERLANDS, LocalDate.of(2027, Month.FEBRUARY, 8), 4L);

                driversLicenseRepository.saveAll(List.of(driversLicenseOne,driversLicenseTwo, driversLicenseThree, driversLicenseFour, driversLicenseFive, driversLicenseSix));

//          PRE CARS
                Car carOne =
                        new FuelCar("97ZNXT", Make.MercedesBenz, "R350L (AMG)", FuelType.DIESEL, 65, 9, 3L, 35, totalCostOwnership.TotalCostOwnershipFuel(65, 9, FuelType.DIESEL));
                Car carTwo =
                        new FuelCar("XS63VK", Make.Ferrari, "Testarossa", FuelType.PETROL, 80, 5, 5L, 249, totalCostOwnership.TotalCostOwnershipFuel(80, 5, FuelType.PETROL));
                Car carThree =
                        new FuelCar("16RDZJ", Make.Opel, "Vectra", FuelType.LPG, 55, 10, 1L, 24.50, totalCostOwnership.TotalCostOwnershipFuel(55, 10, FuelType.LPG));
                Car carFour = new HydrogenCar("K128VV", Make.Toyota, "Mirai", 2L, 35, 13, 68.50, totalCostOwnership.TotalCostOwnerShipHydrogen(35, 13));
                carRepository.saveAll(List.of(carOne, carTwo, carThree, carFour));

//          PRE RESERVATIONS
                Reservation reservationOne = new Reservation(
                        3L,
                        "ZK658B??",
                        carService.getRentFromCar("ZK658B") * Period.between(LocalDate.of(2021, Month.DECEMBER, 1),
                                LocalDate.of(2021, Month.DECEMBER, 5)).getDays(), 1000, 1000 * totalCostOwnership.TotalCostOwnershipFuel(75, 12, FuelType.DIESEL),
                        LocalDate.of(2021, Month.DECEMBER, 1),
                        LocalDate.of(2021, Month.DECEMBER, 5)
                );
                Reservation reservationTwo = new Reservation(
                        6L,
                        "XS63VK",
                        carService.getRentFromCar("XS63VK") * Period.between(
                                LocalDate.of(2022, Month.JANUARY, 21),
                                LocalDate.of(2022, Month.DECEMBER, 27)).getDays(), 750, 750 * totalCostOwnership.TotalCostOwnershipFuel(80, 5, FuelType.PETROL),
                        LocalDate.of(2022, Month.JANUARY, 21),
                        LocalDate.of(2022, Month.DECEMBER, 27)
                );
                Reservation reservationThree = new Reservation(
                        6L,
                        "XS63VK",
                        carService.getRentFromCar("XS63VK") * Period.between(
                                LocalDate.of(2022, Month.MARCH, 27),
                                LocalDate.of(2022, Month.APRIL, 5)).getDays(), 2500, 2500 * totalCostOwnership.TotalCostOwnershipFuel(80, 5, FuelType.PETROL),
                        LocalDate.of(2022, Month.MARCH, 27),
                        LocalDate.of(2022, Month.APRIL, 5)
                );
                Reservation reservationFour = new Reservation(
                        1L,
                        "K536DS",
                        carService.getRentFromCar("K536DS") * Period.between(
                                LocalDate.of(2021, Month.NOVEMBER, 23),
                                LocalDate.of(2021, Month.DECEMBER, 18)).getDays(), 750, 750 * totalCostOwnership.TotalCostOwnerShipElectric(55, 13),
                        LocalDate.of(2021, Month.NOVEMBER, 23),
                        LocalDate.of(2021, Month.DECEMBER, 18)
                );
                Reservation reservationFive = new Reservation(
                        2L,
                        "PM0373",
                        carService.getRentFromCar("PM0373") * Period.between(
                                LocalDate.of(2022, Month.JULY, 8),
                                LocalDate.of(2022, Month.JULY, 19)).getDays(), 3000, 3000 * totalCostOwnership.TotalCostOwnershipFuel(70, 8, FuelType.PETROL),
                        LocalDate.of(2022, Month.JULY, 8),
                        LocalDate.of(2022, Month.JULY, 19)
                );
                Reservation reservationSix = new Reservation(
                        2L,
                        "ZK658B",
                        carService.getRentFromCar("ZK658B") * Period.between(
                                LocalDate.of(2022, Month.JULY, 8),
                                LocalDate.of(2022, Month.JULY, 19)).getDays(), 2250, 2250 * totalCostOwnership.TotalCostOwnershipFuel(75, 12, FuelType.DIESEL),
                        LocalDate.of(2022, Month.JULY, 8),
                        LocalDate.of(2022, Month.JULY, 19),
                        PaymentEnum.PAID
                );
                reservationRepository.saveAll(List.of(reservationOne, reservationTwo, reservationThree, reservationFour, reservationFive, reservationSix));
//
//           PRE UPDATE PAYMENTS
                reservationController.updateReservation(2L, null, null, PaymentEnum.PAID);
                reservationController.updateReservation(3L, null, null, PaymentEnum.PAID);
                reservationController.updateReservation(5L, null, null, PaymentEnum.PAID);

//            INPUT FAKE IMAGES
//                CARS
                carController.addCarImage("ZK658B", "1");
                carController.addCarImage("VLT74G", "2");
                carController.addCarImage("ZL948B", "3");
                carController.addCarImage("PM0373", "4");
                carController.addCarImage("K536DS", "5");
                carController.addCarImage("97ZNXT", "6");
                carController.addCarImage("XS63VK", "7");
                carController.addCarImage("16RDZJ", "8");
                carController.addCarImage("K128VV", "9");
//                CUSTOMERS
                customerController.addCustomerImage(1L, "1");
                customerController.addCustomerImage(2L, "2");
                customerController.addCustomerImage(3L, "3");
                customerController.addCustomerImage(4L, "4");
                customerController.addCustomerImage(5L, "5");
                customerController.addCustomerImage(6L, "6");


            };


        }
    }
}
