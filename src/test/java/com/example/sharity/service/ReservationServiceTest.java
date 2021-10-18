package com.example.sharity.service;

import com.example.sharity.entity.car.Car;

import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.exception.NotFoundException;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.repository.PayoutRepository;
import com.example.sharity.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock private ReservationRepository reservationRepository;
    @Mock private CarRepository carRepository;
    @Mock private PayoutRepository payoutRepository;
    @Mock private CustomerRepository customerRepository;

    private ReservationService sut;

    @BeforeEach
    public void beforeEach() {
        sut = new ReservationService(reservationRepository, payoutRepository, carRepository, customerRepository);
    }


    @Test
    public void addReservation_calculatesCorrectRent() {
        // Arrange
        Reservation given = mock(Reservation.class);
        when(given.getLicensePlate()).thenReturn("KNTK01");
        when(given.getPeriod()).thenReturn(Period.ofDays(4));

        Car car = mock(Car.class);
        when(car.getPricePerDay()).thenReturn(100.00);
        when(carRepository.findCarByLicensePlate("KNTK01")).thenReturn(Optional.of(car));

        // Act
        sut.addReservation(given);

        // Assert
        verify(given, times(1)).setRent(400.0);
    }

    @Test
    public void FindReservationsExceptionThrown() {
        // Act
        given(reservationRepository.findAll()).willReturn(Collections.emptyList());
        // Assert
        assertThatThrownBy(() -> sut.findReservations())
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Reservations not found");
    }

    @Test
    void canAddReservation(){
        // Arrange
        Reservation reservation = new Reservation(7L, "KNTK01", 100, 150, 250.00, LocalDate.of(2021, Month.JANUARY, 1),
                LocalDate.of(2021, Month.JANUARY, 5));
        Car car = mock(Car.class);
        when(carRepository.findCarByLicensePlate("KNTK01")).thenReturn(Optional.of(car));
        // Act
        sut.addReservation(reservation);
        // Assert
        ArgumentCaptor<Reservation> reservationArgumentCaptor =
                ArgumentCaptor.forClass(Reservation.class);

        verify(reservationRepository).save(reservationArgumentCaptor.capture());

        Reservation capturedReservation = reservationArgumentCaptor.getValue();
        // Assert
        assertThat(capturedReservation).isEqualTo(reservation);
    }
}