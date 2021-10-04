package com.example.sharity.service;


import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

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

    @Transactional
    public void updateReservation(int reservationNumber, LocalDate startDate, LocalDate endDate) {
       Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).orElseThrow(() -> new IllegalStateException("Reservation with " + reservationNumber + " does not exist"));

    }


}


//    public Reservation getReservations(int reservationNumber){
//        return reservationRepository.stream().filter(r -> r.getId() == (reservationNumber)).findFirst().get();
//    }
//
//
//    public void updateReservation(int reservationNumber, Reservation reservation) {
//        for (int i = 0; i < reservations.size(); i++){
//            Reservation r = reservations.get(i);
//            if(r.getId() == (reservationNumber)){
//                reservations.set(i, reservation);
//                return;
//            }
//        }
//    }
//
//    public void deleteReservation(int reservationNumber) {
//        reservations.removeIf(r -> r.getId() == (reservationNumber));
//    }
//}