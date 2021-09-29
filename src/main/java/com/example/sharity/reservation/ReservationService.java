package com.example.sharity.reservation;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ReservationService {

     private List<Reservation> reservations = new ArrayList<>(Arrays.asList(
                new Reservation(200.0 )
        ));

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    public Reservation getReservation(int reservationNumber){
        return reservations.stream().filter(r -> r.getId() == (reservationNumber)).findFirst().get();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void updateReservation(int reservationNumber, Reservation reservation) {
        for (int i = 0; i < reservations.size(); i++){
            Reservation r = reservations.get(i);
            if(r.getId() == (reservationNumber)){
                reservations.set(i, reservation);
                return;
            }
        }
    }

    public void deleteReservation(int reservationNumber) {
        reservations.removeIf(r -> r.getId() == (reservationNumber));
    }
}