package com.example.sharity.service;

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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

//interne logica

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

    public Reservation addReservation(Reservation reservation) {
        String licensePlate = reservation.getLicensePlate();
        Car car = carRepository.getById(licensePlate);
        double rent = car.getRent() * (Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays());

        reservation.setRent(rent);

        return reservationRepository.save(reservation);
    }


    public void updateReservation(Long reservationNumber, LocalDate startDate, LocalDate endDate, PaymentEnum paymentEnum) {
        Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).orElseThrow(() -> new IllegalStateException("Reservation with reservationNumber " + reservationNumber + " not found."));
        Optional<Payout> reservationOptional = payoutRepository.findPayoutByReservationNumber(reservationNumber);

        //        CHECK IF PAYMENT ALREADY HAD BEEN COMPLETED, SO NO DOUBLE DATA GOES INTO DATABASE
        Optional<Payout> payoutOptional = payoutRepository.findPayoutByReservationNumber(reservationNumber);
        if (payoutOptional.isPresent()) {
            throw new IllegalStateException("Payment had already been completed");
        } else {

            //          GETTERS FOR UPDATING PAYMENT TABLE
            String licensePlate = reservation.getLicensePlate();
            Car car = carRepository.findCarByLicensePlate(licensePlate).orElseThrow(() -> new IllegalStateException("Car with licenseplate " + licensePlate + " not found."));
            Long customerNumber = car.getCustomerNumber();
            Customer customer = customerRepository.findById(customerNumber).orElseThrow(()-> new IllegalStateException("Customer with customernumber " + customerNumber + " not found."));
            double rent = NumberRounder.roundDouble((reservation.getRent()), 2);

//            UPDATE RENTPRICE
            double rent = car.getPricePerDay() * Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays();
            reservation.setRent(rent);

//            CHECK IF PAYMENTENUM IS SET TO UPDATE, IF ALREADY PAID, IT CAN'T RE-OPEN BECAUSE A PAYMENT CAN BE DONE TWICE
            if (paymentEnum == PaymentEnum.PAID) {
                reservation.setPaymentEnum(paymentEnum);

                //          GETTER FOR UPDATING PAYMENT TABLE
                Customer customer = customerRepository.findCustomerByCustomerNumber(car.getCustomerNumber()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carowner of car with licenseplate " + reservation.getLicensePlate() + " of customer number " + reservation.getCustomerNumber() + " not in database"));

                //      SETTERS FOR UPDATING PAYMENT TABLE
                double payoutAmount = NumberRounder.roundDouble((rent * 0.79), 2);
                double tax = rent - payoutAmount;
                customer.setBalance(customer.getBalance() + payoutAmount);
                Payout payout = new Payout(reservationNumber, payoutAmount, tax, car.getCustomerNumber());

                payoutRepository.save(payout);
                customerRepository.save(customer);

            } else if (paymentEnum == PaymentEnum.OPEN) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment is already PAID and can not be re-opened");
            }

            reservationRepository.save(reservation);
        }
    }

    public void deleteReservation(Long reservationNumber) {
        Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).orElseThrow(() -> new IllegalStateException("Reservation with reservationNumber " + reservationNumber + " not found."));
        reservationRepository.delete(reservation);
    }

    public Optional <Reservation> findReservation(Long reservationNumber) {
        Optional <Reservation> reservationOptional = reservationRepository.findReservationByReservationNumber(reservationNumber);
        if (reservationOptional.isEmpty()) {
            throw new IllegalStateException("Reservation with reservationNumber " + reservationNumber + " not found.");

//                    ResponseStatusException(HttpStatus.NOT_FOUND);

        }

        return reservationRepository.findReservationByReservationNumber(reservationNumber);
        }

}
