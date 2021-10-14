package com.example.sharity.service;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.entity.reservation.PaymentEnum;
import com.example.sharity.entity.Payout;
import com.example.sharity.entity.reservation.Reservation;
import com.example.sharity.exception.NotFoundException;
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

    public List<Reservation> findReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addReservation(Reservation reservation) {
        //        CHECK IF CAR IS AVAILABLE IN THE PERIOD OF RENTAL
        Optional<Reservation> reservationOptional = reservationRepository.checkCarAvailability(reservation.licensePlate, reservation.getStartDate(), reservation.getEndDate());
        if (reservationOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not available in this period");
        }

//        GETTERS TO FIND CARDATA
        Car car = carRepository.findCarByLicensePlate(reservation.licensePlate).
                orElseThrow(()-> new IllegalStateException("Car with license plate " + reservation.getLicensePlate() + " not in database"));
        double dayRent = car.getPricePerDay() * Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays();
        double packageprice = car.getPricePerKm() * reservation.getKmPackage();
        double totalRent = packageprice + dayRent;

//        SET THE RENT * DAYS OF RENT
        reservation.setPackagePrice(packageprice);
        reservation.setRent(NumberRounder.roundDouble(totalRent, 2));


//        SAVE HERE AND NOT AT THE END, BECAUSE OTHERWISE THE NEW PAYOUT CAN'T FIND A RESERVATIONNUMBER
        reservationRepository.save(reservation);

//        IF RESERVATION IS PAID DIRECTLY AT RESERVATIONMOMENT
        if (reservation.getPaymentEnum() == PaymentEnum.PAID){
//            GETTERS TO GET OWNER OF THE CAR
            Long customerNumber = car.getCustomerNumber();
            Customer customer = customerRepository.findById(customerNumber).
                    orElseThrow(()->new NotFoundException("Customer", customerNumber));

            double sharityProfit = NumberRounder.roundDouble((totalRent * 0.1), 2);
            double tax = NumberRounder.roundDouble((totalRent * 0.21), 2);
            double payoutAmount = NumberRounder.roundDouble((totalRent - sharityProfit - tax), 2);

            //        SETTERS TO SET THE PAYOUT AND WRITE ON BALANCE OF OWNER

            Payout payout = new Payout(reservation.getReservationNumber(), sharityProfit, payoutAmount, tax, customerNumber);
            payout.setPayoutAmount(payoutAmount);
            payout.setSharityProfit(sharityProfit);
            payout.setTax(tax);
            payoutRepository.save(payout);

            customer.setBalance(customer.getBalance() + payoutAmount);
            customerRepository.save(customer);

        }
        return reservation;
    }


    public Reservation updateReservation(Long reservationNumber, LocalDate startDate, LocalDate endDate, PaymentEnum paymentEnum) {
        //        CHECK IF CAR & RESERVATION ARE IN DATABASE
        Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation with reservation number " + reservationNumber + " not in database"));
        Car car = carRepository.findCarByLicensePlate(reservation.getLicensePlate()).
                orElseThrow(()-> new IllegalStateException("Rented car with license plate " + reservation.getLicensePlate() + " not in database"));

        //        CHECK IF CAR IS AVAILABLE IN THE PERIOD OF RENTAL
        Optional<Reservation> reservationOptional = reservationRepository.checkCarAvailability(reservation.licensePlate, startDate, endDate);
        if (reservationOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not available in this period");
        }

        //        CHECK IF PAYMENT ALREADY HAD BEEN COMPLETED, SO NO DOUBLE DATA GOES INTO DATABASE
        Optional<Payout> payoutOptional = payoutRepository.findPayoutByReservationNumber(reservationNumber);
        if (payoutOptional.isPresent()) {
            throw new IllegalStateException("Payment had already been completed");
        } else {

//            CHECK IF RESERVATIONDATES WERE SET TO UPDATE
            if (reservation.getStartDate() != startDate && startDate != null) {
                reservation.setStartDate(startDate);
            }
            if (reservation.getEndDate() != endDate && endDate != null) {
                reservation.setEndDate(endDate);
            }

//            UPDATE RENTPRICE
            double dayRent = car.getPricePerDay() * Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays();
            double packageprice = car.getPricePerKm() * reservation.getKmPackage();
            double totalRent = NumberRounder.roundDouble((dayRent + packageprice), 2);
            reservation.setRent(totalRent);
            reservationRepository.save(reservation);

//            CHECK IF PAYMENTENUM IS SET TO UPDATE, IF ALREADY PAID, IT CAN'T RE-OPEN BECAUSE A PAYMENT CAN BE DONE TWICE
            if (paymentEnum == PaymentEnum.PAID) {
                reservation.setPaymentEnum(paymentEnum);

                //          GETTER FOR UPDATING PAYMENT TABLE
                Customer customer = customerRepository.findById(car.getCustomerNumber()).orElseThrow(()->new NotFoundException("Customer", car.getCustomerNumber()));

                //      SETTERS FOR UPDATING PAYMENT TABLE
                double sharityProfit = NumberRounder.roundDouble((totalRent * 0.1), 2);
                double tax = NumberRounder.roundDouble((totalRent * 0.21), 2);
                double payoutAmount = NumberRounder.roundDouble((totalRent - sharityProfit - tax), 2);
                
                Payout payout = new Payout(reservationNumber, sharityProfit, payoutAmount, tax, car.getCustomerNumber());
                payout.setPayoutAmount(payoutAmount);
                payout.setSharityProfit(sharityProfit);
                payout.setTax(tax);
                payoutRepository.save(payout);

                customer.setBalance(customer.getBalance() + payoutAmount);
                customerRepository.save(customer);

            } else if (paymentEnum == PaymentEnum.OPEN) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Payment is already PAID and can not be re-opened");
            }
            reservationRepository.save(reservation);
        }
        return reservation;
    }

    public void deleteReservation(Long reservationNumber) {
        Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).
                orElseThrow(() -> new NotFoundException("Reservation number",reservationNumber));
        reservationRepository.delete(reservation);
    }


    public Reservation findReservation(Long reservationNumber) {
        Reservation reservation = reservationRepository.findById(reservationNumber).orElseThrow(()->new NotFoundException("Reservation", reservationNumber));

        return reservationRepository.getById(reservationNumber);
    }

}