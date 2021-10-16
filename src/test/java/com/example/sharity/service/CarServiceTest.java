package com.example.sharity.service;



import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.car.Insurance;
import com.example.sharity.entity.car.carTypes.FuelCar;
import com.example.sharity.entity.car.enums.FuelType;
import com.example.sharity.entity.car.enums.Make;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Period;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.repository.PayoutRepository;
import com.example.sharity.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Period;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CarServiceTest
{
    @InjectMocks
    private CarService sut;

    @Mock
    private CarRepository carRepository;
    private InsuranceRepository insuranceRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateCarTestNewRent() {
        // Arrange
        Car car = mock(Car.class);
        when(car.getLicensePlate()).thenReturn("KTKN01");
        Make make = Make.MercedesBenz;
        long customerNumber = 12;
        FuelCar fuelCar = sut.addFuelCar("KTKN01", customerNumber, make, "model", 100.00, FuelType.PETROL, 1, 1);
        when(carRepository.findById(anyString())).thenReturn(Optional.ofNullable(fuelCar));

        double updatedRent = 400.0;
        // Act
//
//        when(car.getPricePerDay()).thenReturn(100.00);
        sut.updateCar(car.getLicensePlate(), updatedRent);

        // Assert Equals
        assertEquals(updatedRent, fuelCar.getPricePerDay());
    }


}
