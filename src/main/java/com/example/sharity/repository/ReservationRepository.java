package com.example.sharity.repository;

import com.example.sharity.entity.customer.Customer;
import com.example.sharity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation,String> {

    @Query("SELECT r.reservationNumber FROM Reservation r WHERE r.reservationNumber =?1")
    Optional<Reservation> findReservationByReservationNumber(int reservationNumber);

    @Query("SELECT c.customerNumber FROM Customer c WHERE c.customerNumber =?1")
    Optional<Customer> findReservationByCustomerNumber(Long customerNumber);

}
