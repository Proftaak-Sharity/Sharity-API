package com.example.sharity.repository;

import com.example.sharity.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.reservationNumber =?1")
    Optional<Reservation> findReservationByReservationNumber(Long reservationNumber);

}
