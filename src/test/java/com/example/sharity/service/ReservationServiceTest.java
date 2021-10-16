package com.example.sharity.service;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.repository.PayoutRepository;
import com.example.sharity.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Period;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ReservationServiceTest {

    private ReservationService sut;

    @BeforeEach
    public void beforeEach() {
        ReservationRepository reservationRepository = mock(ReservationRepository.class);
        PayoutRepository payoutRepository = mock(PayoutRepository.class);
        CarRepository carRepository = mock(CarRepository.class);
        CustomerRepository customerRepository = mock(CustomerRepository.class);

        Car car = mock(Car.class);

        when(car.getPricePerDay()).thenReturn(100.00);
        when(car.getCustomerNumber()).thenReturn(1L);

        when(carRepository.findCarByLicensePlate("KNTK01")).thenReturn(Optional.of(car));

        Customer customer = mock(Customer.class);
        when(customer.getCustomerNumber()).thenReturn(3L);

        sut = new ReservationService(reservationRepository, payoutRepository, carRepository, customerRepository);
    }

    @Test
    public void addReservation_calculatesCorrectRent() {
        // Arrange
        Reservation given = mock(Reservation.class);
        when(given.getLicensePlate()).thenReturn("KNTK01");
        when(given.getPeriod()).thenReturn(Period.ofDays(4));

        // Act
        sut.addReservation(given);

        // Assert
        verify(given, times(1)).setRent(400.0);
    }
}