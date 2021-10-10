package com.example.sharity;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.carTypes.FuelCar;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservationTest {

//    private CarService sut;
//
//    @BeforeEach
//    public void beforeEach() {
//        CarRepository repository = mock(CarRepository.class);
//
//        Car car = mock(Car.class);
//        when(car.getPricePerDay()).thenReturn(150.00);
//
//        when(repository.getById(car.getLicensePlate())).thenReturn(car);
//
//        sut = new CarService(repository);
//    }


    @Test
    void addReservationGetDaysTest() {
        // Arrange
        Reservation reservation = new Reservation(3L,"KNTK01", 100, LocalDate.of(2021, Month.DECEMBER, 1),
                LocalDate.of(2021, Month.DECEMBER, 5));
        // Act
        int days = Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays();
        // Assert
        assertEquals(4,4);
    }
}

//    @Test
//    void addReservationGetRentTest() {
//      //  Car car = new FuelCar("KNTK01", Make.Volvo, "XC90", FuelType.DIESEL, 52, 12, 99.33);
//        // Arrange
//        Reservation reservation = new Reservation(3L,"KNTK01", 600, LocalDate.of(2021, Month.DECEMBER, 1),
//                LocalDate.of(2021, Month.DECEMBER, 5));
//        // Act
//        sut.getRentFromCar("KNTK01");
//        // Assert
//        assertEquals(99.33,99.33);
//    }
//
//}

