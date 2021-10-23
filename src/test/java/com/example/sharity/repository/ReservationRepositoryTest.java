package com.example.sharity.repository;

import com.example.sharity.reservation.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ReservationRepositoryTest {

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void init(){ {
        MockitoAnnotations.openMocks(this);
    }
        Reservation reservation = new Reservation(7L, "XX95ZR", 100, 150, 250.00, LocalDate.of(2021, Month.JANUARY, 1),
                LocalDate.of(2021, Month.JANUARY, 5));
        when(reservationRepository.findReservationByReservationNumber(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.checkCarAvailability("XX95ZR", LocalDate.of(2021, Month.JANUARY, 1),
                LocalDate.of(2021, Month.JANUARY, 5))).thenReturn(Optional.of(reservation));
    }

    @Test
    void shouldFindReservationByReservationNumber() {
        // Act
        Optional<Reservation> expected = reservationRepository.findReservationByReservationNumber(1L);
        // Assert
        assertThat(expected).isPresent();
    }

    @Test
    void checkCarAvailabilityIsPresent() {
        // Act
        Optional<Reservation> expected = reservationRepository.checkCarAvailability("XX95ZR",LocalDate.of(2021, Month.JANUARY, 1),
                LocalDate.of(2021, Month.JANUARY, 5));
        // Assert
        assertThat(expected).isPresent();
    }
}