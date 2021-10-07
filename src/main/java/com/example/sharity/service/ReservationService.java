package com.example.sharity.service;


import com.example.sharity.abstracts.NumberRounder;
import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.entity.reservation.PaymentEnum;
import com.example.sharity.entity.reservation.Payout;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.repository.PayoutRepository;
import com.example.sharity.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PayoutRepository payoutRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, PayoutRepository payoutRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.payoutRepository = payoutRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updateReservation(Long reservationNumber, LocalDate startDate, LocalDate endDate, PaymentEnum paymentEnum) {
        Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).orElseThrow(() -> new IllegalStateException("Reservation unknown"));
        Optional<Payout> reservationOptional = payoutRepository.findPayoutByReservationNumber(reservationNumber);

        //        CHECK IF PAYMENT ALREADY HAD BEEN COMPLETED, SO NO DOUBLE DATA GOES INTO DATABASE
        if ((reservationOptional).isPresent()) {
            throw new IllegalStateException("Payment had already been completed");
        } else {

            //          GETTERS FOR UPDATING PAYMENT TABLE
            String licensePlate = reservation.getLicensePlate();
            Car car = carRepository.findCarByLicensePlate(licensePlate).orElseThrow(() -> new IllegalStateException("LicensePlate unknown"));
            Long customerNumber = car.getCustomerNumber();
            Customer customer = customerRepository.findById(customerNumber).orElseThrow(()-> new IllegalStateException("Customer unknown"));
            double rent = NumberRounder.roundDouble((reservation.getRent()), 2);

            //      SETTERS FOR UPDATING PAYMENT TABLE
            reservation.setPaymentEnum(PaymentEnum.PAID);
            double payoutAmount = NumberRounder.roundDouble((rent * 0.79), 2);
            double tax = NumberRounder.roundDouble((rent * 0.21), 2);
            customer.setBalance(NumberRounder.roundDouble(customer.getBalance(), 2) + payoutAmount);

            Payout payout = new Payout(reservationNumber, payoutAmount, tax, customerNumber);

            //          SAVE TO DATABASE
            payoutRepository.save(payout);
            reservationRepository.save(reservation);
            customerRepository.save(customer);
        }
    }

    public void deleteReservation(Long reservationNumber) {
        Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).orElseThrow(() -> new IllegalStateException("Reservation unknown"));
        reservationRepository.delete(reservation);
    }

    public Optional <Reservation> findReservation(Long reservationNumber) {
        Optional <Reservation> reservationOptional = reservationRepository.findReservationByReservationNumber(reservationNumber);
        if (reservationOptional.isEmpty()) {
            throw new IllegalStateException("Reservation unknown");
        }

        return reservationRepository.findReservationByReservationNumber(reservationNumber);
        }

}
