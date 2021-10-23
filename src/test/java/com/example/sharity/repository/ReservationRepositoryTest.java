package com.example.sharity.repository;

import com.example.sharity.reservation.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
    }


    @Test
    void shouldFindReservationByReservationNumber() {
        // Act
        Optional<Reservation> expected = reservationRepository.findReservationByReservationNumber(1L);
        // Assert
        assertThat(expected).isPresent();
    }

    @Test
    @S
    void checkCarAvailabilityIsPresent() {
        // Arrange
        Reservation reservation = new Reservation(3L, "KNTK01", 100, 150, 250.00, LocalDate.of(2021, Month.DECEMBER, 1),
                LocalDate.of(2021, Month.DECEMBER, 5));
        sut.save(reservation);

        // Act
        Optional<Reservation> expected = sut.checkCarAvailability("KNTK01",LocalDate.of(2021, Month.DECEMBER, 1),
                LocalDate.of(2021, Month.DECEMBER, 5));
        // Assert
        assertThat(expected).isPresent();
    }
}