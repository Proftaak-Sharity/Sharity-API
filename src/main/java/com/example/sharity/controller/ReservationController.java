package com.example.sharity.controller;

import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @PostMapping
    public void addReservation(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
    }

    @PutMapping(path = "{reservationNumber}")
    public void updateReservation(
            @PathVariable("reservationNumber") int reservationNumber,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate){
        reservationService.updateReservation(reservationNumber, startDate, endDate);
    }
    @DeleteMapping(path = "{reservationNumber}")
    public void deleteReservation(
            @PathVariable("reservationNumber") int reservationNumber){
        reservationService.deleteReservation(reservationNumber);
    }

}