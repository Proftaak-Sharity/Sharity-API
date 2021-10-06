package com.example.sharity.controller;

import com.example.sharity.entity.reservation.PaymentEnum;
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

    @GetMapping(path = "{reservationNumber}")
    public void getReservation(
            @PathVariable("reservationNumber") Long reservationNumber) {
        reservationService.getReservation(reservationNumber);
    }

    @PostMapping
    public void addReservation(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
    }

    @PutMapping(path = "{reservationNumber}")
    public void updateReservation(
            @PathVariable("reservationNumber") Long reservationNumber,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) PaymentEnum paymentEnum) {
        reservationService.updateReservation(reservationNumber, startDate, endDate, paymentEnum);
    }

//    @PutMapping(path = "{reservationNumber}")
//    public void updatePayment(
//            @PathVariable("reservationNumber") Long reservationNumber,
//            @RequestParam(required = false) PaymentEnum paymentEnum) {
//        reservationService.updatePayment(reservationNumber, paymentEnum);
//    }

    @DeleteMapping(path = "{reservationNumber}")
    public void deleteReservation(
            @PathVariable("reservationNumber") Long reservationNumber) {
        reservationService.deleteReservation(reservationNumber);
            }
}
