package com.example.sharity.controller;

import com.example.sharity.repository.ReservationRepository;
import com.example.sharity.reservation.PaymentEnum;
import com.example.sharity.reservation.Reservation;
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
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping(path = "/customer/{customerNumber}")
    public Optional<List<Reservation>> getReservations(@PathVariable("customerNumber") Long customerNumber) {
        return reservationService.getReservations(customerNumber);
    }

    @GetMapping(path = "{reservationNumber}")
    public Reservation getReservation(@PathVariable("reservationNumber") Long reservationNumber) {
        return reservationService.getReservation(reservationNumber);
    }

    @GetMapping(path = "/rentedlicenseplates")
    public Optional<List<Reservation>> getRentedCars(
             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        return reservationService.getRentedCars(startDate, endDate);
    }

    @PostMapping(path = {"/addreservation"})
        public Long addReservation(@RequestParam Long customerNumber,
                                          @RequestParam String licensePlate,
                                          @RequestParam (required = false) Integer kmPackage,
                                          @RequestParam @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate startDate,
                                          @RequestParam @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate endDate,
                                          @RequestParam Double rent,
                                          @RequestParam Double packagePrice,
                                          @RequestParam PaymentEnum payment) {

        return reservationService.addReservation(customerNumber, licensePlate, kmPackage, startDate, endDate, rent, packagePrice, payment);
    }

    @PutMapping(path = "/payment/{reservationNumber}")
    public PaymentEnum updatePayment(@PathVariable("reservationNumber") Long reservationNumber,
                                @RequestParam PaymentEnum payment) {

        Reservation reservation = reservationRepository.getById(reservationNumber);
        reservation.setPayment(payment);
        reservationRepository.save(reservation);

        return reservationRepository.getById(reservationNumber).getPayment();
    }

//    Only for getting the balance of the preDataConfig customers in database (not being used in APK)
    @PutMapping(path = "{reservationNumber}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable("reservationNumber") Long reservationNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) PaymentEnum payment) {
        System.out.println("Reservation Controller");
        Reservation updateReservation = reservationService.updateReservation(reservationNumber, startDate, endDate, payment);
        return ResponseEntity.created(URI.create("/api/reservations/" + updateReservation.getReservationNumber())).body(updateReservation);
    }


}
