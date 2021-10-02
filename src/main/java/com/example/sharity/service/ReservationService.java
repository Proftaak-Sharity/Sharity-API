package com.example.sharity.service;

import com.example.sharity.repository.ReservationRepository;
import com.example.sharity.entity.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private int reservationNumber;
    private LocalDate startDate;
    private LocalDate endDate;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updateReservation(int reservationNumber, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = reservationRepository.findById(reservationNumber).orElseThrow(() ->
                new IllegalStateException("Reservation with number " + reservationNumber + " does not exist"));
        reservationRepository.save(reservation);
    }

    public void deleteReservation(int reservationNumber){
        reservationRepository.deleteById(reservationNumber);
    }
}