package com.example.sharity.controller;

import com.example.sharity.reservation.PaymentEnum;
import com.example.sharity.reservation.Reservation;
import com.example.sharity.exception.CrudAllException;
import com.example.sharity.exception.InputNotAllowedException;
import com.example.sharity.exception.car.DeletedException;
import com.example.sharity.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> findReservations() {
        return reservationService.findReservations();
    }

    @GetMapping(path = "{reservationNumber}")
    public Reservation findReservation(
            @PathVariable("reservationNumber") Long reservationNumber) {
        return reservationService.findReservation(reservationNumber);
    }

    @GetMapping(path = "/rentedLicences")
    public List<Reservation> findRentedCars(
             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        System.out.println("test");;
        System.out.println(startDate);;
        return reservationService.checkRentedCars(startDate, endDate);
    }

    @GetMapping(path ={"/customer/{customerNumber}"})
    public List<Reservation>findReservationsByCustomerNumber(
            @PathVariable("customerNumber") Long customerNumber) {
        return reservationService.findReservationsByCustomerNumber(customerNumber);
    }

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
//        EXCEPTIONS
        if (reservation.getReservationNumber() != null) {
            throw new InputNotAllowedException("reservation number(" + reservation.getReservationNumber() + ")");
        }

        Reservation newReservation = reservationService.addReservation(reservation);
        return ResponseEntity.created(URI.create("/api/reservations/" + newReservation.getReservationNumber())).body(newReservation);
    }

    @PutMapping(path = "{reservationNumber}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable("reservationNumber") Long reservationNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) PaymentEnum paymentEnum) {
        Reservation updateReservation = reservationService.updateReservation(reservationNumber, startDate, endDate, paymentEnum);
        return ResponseEntity.created(URI.create("/api/reservations/" + updateReservation.getReservationNumber())).body(updateReservation);
    }

    @DeleteMapping(path = "{reservationNumber}")
    public void deleteReservation(
            @PathVariable("reservationNumber") Long reservationNumber) {
        reservationService.deleteReservation(reservationNumber);
        throw new DeletedException("Reservation");
    }

    //  IF NO RESERVATIONNUMBER INSERTED
    @DeleteMapping
    public void deleteAllReservations() {
        throw new CrudAllException("delete", "reservations");
    }

    @PutMapping
    public void updateAllReservations() {
        throw new CrudAllException("update", "reservations");
    }
}
