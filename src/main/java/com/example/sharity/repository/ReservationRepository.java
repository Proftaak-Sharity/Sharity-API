package com.example.sharity.repository;

import com.example.sharity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


//databse
@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.reservationNumber =?1")
    Optional<Reservation> findReservationByReservationNumber(Long reservationNumber);

    ///// find reservations by customerNumber
    @Query("SELECT r FROM Reservation r WHERE r.customerNumber =?1")
    List<Reservation> findReservationsByCustomerNumber(Long customerNumber);

    //        CHECK IF CAR IS AVAILABLE IN THE PERIOD OF RENTAL
    @Query("SELECT r FROM Reservation r WHERE r.licensePlate = ?1 AND (?2 BETWEEN r.startDate AND r.endDate OR ?3 between r.startDate AND r.endDate)")
    Optional<Reservation> checkCarAvailability(String licensePlate, LocalDate startDate, LocalDate endDate);

//    //        CHECK what cars are available
//    @Query("SELECT distinct r.licensePlate FROM Reservation r WHERE (?1 NOT BETWEEN r.startDate AND r.endDate OR ?3 NOT between r.startDate AND r.endDate)")
//    Optional<Reservation> checkAvailableCars(LocalDate startDate, LocalDate endDate);
}
