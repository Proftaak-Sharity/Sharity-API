package com.example.sharity.repository;

import com.example.sharity.entity.reservation.Payout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayoutRepository extends JpaRepository<Payout, Long>
{



}
