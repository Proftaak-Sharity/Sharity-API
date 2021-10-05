package com.example.sharity.service;


import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.reservation.PaymentEnum;
import com.example.sharity.entity.reservation.Payout;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.repository.PayoutRepository;
import com.example.sharity.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PayoutRepository payoutRepository;
    private final CarRepository carRepository;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, PayoutRepository payoutRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.payoutRepository = payoutRepository;
        this.carRepository = carRepository;
    }
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }


    @Transactional
    public void updatePayment(Long reservationNumber, PaymentEnum paymentEnum) {
        Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).orElseThrow(()-> new IllegalStateException("fout Reservation"));

        double rent = reservation.getRent();
        String licensePlate = reservation.getLicensePlate();

        Car car = carRepository.findCarByLicensePlate(licensePlate).orElseThrow(()-> new IllegalStateException("fout licensePlate"));
        Long customerNumber = car.getCustomerNumber();

        reservation.setPaymentEnum(PaymentEnum.PAID);

        Payout payout = new Payout(rent, customerNumber);

        payoutRepository.save(payout);

    }

//    public void addReservation(Reservation reservation) {
//        reservationRepository.save(reservation);
//    }

//    @Transactional
//    public void updateReservation(Long reservationNumber, LocalDate startDate, LocalDate endDate) {
//       Reservation reservation = (Reservation) reservationRepository.findReservationByReservationNumber(reservationNumber);
//
//    }


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