package com.example.sharity.repository;

import com.example.sharity.entity.Payout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayoutRepository extends JpaRepository<Payout, Long>
{
    @Query("select p FROM Payout p WHERE p.reservationNumber = ?1")
    Optional<Payout> findPayoutByReservationNumber(Long reservationNymber);


}
