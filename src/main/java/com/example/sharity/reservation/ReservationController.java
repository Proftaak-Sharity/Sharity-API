package com.example.sharity.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(path = "api/reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @RequestMapping(path = "api/reservations/{reservationNumber}")
    public Reservation getReservation(@PathVariable int reservationNumber) {
        return reservationService.getReservation(reservationNumber);
    }

    @RequestMapping(method=RequestMethod.POST, value="/reservations")
    public void addReservation(@RequestBody Reservation reservation){
        reservationService.addReservation(reservation);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/reservations/{id}")
    public void updateReservation(@RequestBody Reservation reservation, @PathVariable int reservationNumber){
        reservationService.updateReservation(reservationNumber, reservation);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "reservations/{id}")
    public void deleteReservation(@PathVariable int reservationNumber){
        reservationService.deleteReservation(reservationNumber);
    }

}
