package com.example.sharity.reservation;

import com.example.sharity.reservation.Reservation;
import com.example.sharity.reservation.ReservationService;
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
    public List<Reservation> getReservations() {return reservationService.getReservations();
    }

    @PostMapping
    public void addReservation(@RequestBody Reservation reservation) {reservationService.addReservation(reservation);
    }

    @PutMapping(path = "{reservationNumber}")
    public void updateReservation(
            @PathVariable("reservationNumber") int reservationNumber,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate){
        reservationService.updateReservation(reservationNumber, startDate, endDate );
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
