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


    public void updatePayment(Long reservationNumber, PaymentEnum paymentEnum) {

        //        CHECK IF PAYMENT ALREADY HAD BEEN COMPLETED, SO NO DOUBLE DATA GOES INTO DATABASE
        Optional<Payout> reservationOptional = payoutRepository.findPayoutByReservationNumber(reservationNumber);
        if ((reservationOptional).isPresent()) {
            throw new IllegalStateException("Payment had already been completed");
        } else {

            //          GETTERS FOR UPDATING PAYMENT TABLE
            Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).orElseThrow(() -> new IllegalStateException("Reservation unknown"));
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