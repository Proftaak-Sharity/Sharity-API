package com.example.sharity.controller;

import com.example.sharity.entity.reservation.PaymentEnum;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Reservation> getReservations() {return reservationService.getReservations();
    }

//    @PostMapping
//    public void addReservation(@RequestBody Reservation reservation) {reservationService.addReservation(reservation);
//    }

//    @PutMapping(path = "{reservationNumber}")
//    public void updateReservation(
//            @PathVariable("reservationNumber") int reservationNumber,
//            @RequestParam(required = false) LocalDate startDate,
//            @RequestParam(required = false) LocalDate endDate){
//        reservationService.updateReservation(reservationNumber, startDate, endDate );
//    }

    @PutMapping(path = "{reservationNumber}")
    public void updatePayment(
            @PathVariable("reservationNumber") Long reservationNumber,
            @RequestParam(required = false) PaymentEnum paymentEnum)
    {
        reservationService.updatePayment(reservationNumber, paymentEnum);
    }
}



//    @RequestMapping(path = "api/reservations/{reservationNumber}")
//    public Reservation getReservations(@PathVariable int reservationNumber) {
//        return reservationService.getReservations(reservationNumber);
//    }
//
//    @RequestMapping(method=RequestMethod.POST, value="/reservations")
//    public void addReservation(@RequestBody Reservation reservation){
//        reservationService.addReservation(reservation);
//    }
//
//    @RequestMapping(method=RequestMethod.PUT, value="/reservations/{id}")
//    public void updateReservation(@RequestBody Reservation reservation, @PathVariable int reservationNumber){
//        reservationService.updateReservation(reservationNumber, reservation);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "reservations/{id}")
//    public void deleteReservation(@PathVariable int reservationNumber){
//        reservationService.deleteReservation(reservationNumber);
//    }
