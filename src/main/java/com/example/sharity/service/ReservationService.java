package com.example.sharity.service;

import com.example.sharity.exception.CarNotAvailableException;
import com.example.sharity.payout.Payout;
import com.example.sharity.car.Car;
import com.example.sharity.customer.Customer;
import com.example.sharity.reservation.PaymentEnum;
import com.example.sharity.reservation.Reservation;
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
import java.util.Objects;
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
        var reservations = reservationRepository.findAll();
        if (reservations.isEmpty()){
           throw new NotFoundException("Reservations");
        }
        return reservations;
    }

    public void addReservation(Reservation reservation) {
//        CHECK IF CAR IS AVAILABLE IN THE PERIOD OF RENTAL
        Optional<Reservation> reservationOptional = reservationRepository.checkCarAvailability(reservation.getLicensePlate(), reservation.getStartDate(), reservation.getEndDate());
        if (reservationOptional.isPresent()) {
            throw new CarNotAvailableException(reservation.getStartDate(), reservation.getEndDate());
        }

//        GETTERS TO FIND CARDATA
        Car car = carRepository.findCarByLicensePlate(reservation.getLicensePlate()).
                orElseThrow(()-> new NotFoundException("Car", reservation.getLicensePlate()));
        double pricePerDay = car.getPricePerDay();
        int days = reservation.getPeriod().getDays();
        double rent = pricePerDay * days;

        double dayRent = pricePerDay * days;
        double packageprice = car.getPricePerKm() * reservation.getKmPackage();
        double totalRent = packageprice + dayRent;


//        SET THE RENT * DAYS OF RENT
        reservation.setPackagePrice(packageprice);
        reservation.setRent(NumberRounder.roundDouble(totalRent, 2));


//        SAVE HERE AND NOT AT THE END, BECAUSE OTHERWISE THE NEW PAYOUT CAN'T FIND A RESERVATIONNUMBER
        reservationRepository.save(reservation);

//        IF RESERVATION IS PAID DIRECTLY AT RESERVATIONMOMENT
        if (reservation.getPayment() == PaymentEnum.PAID) {
//            GETTERS TO GET OWNER OF THE CAR
            Long customerNumber = car.getCustomerNumber();
            Customer customer = customerRepository.findById(customerNumber).
                    orElseThrow(() -> new NotFoundException("Customer", customerNumber));

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
    }


    public Reservation updateReservation(Long reservationNumber, LocalDate startDate, LocalDate endDate, PaymentEnum paymentEnum) {
        //        CHECK IF CAR & RESERVATION ARE IN DATABASE
        Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).
                orElseThrow(() -> new NotFoundException("Reservation", reservationNumber));
        Car car = carRepository.findCarByLicensePlate(reservation.getLicensePlate()).
                orElseThrow(()-> new NotFoundException("Car" + reservation.getLicensePlate()));

        //        CHECK IF CAR IS AVAILABLE IN THE PERIOD OF RENTAL
        Optional<Reservation> reservationOptional = reservationRepository.checkCarAvailability(reservation.getLicensePlate(), startDate, endDate);
       if (reservationOptional.isPresent() && !Objects.equals(reservationNumber, reservationOptional.get().getReservationNumber())){
           throw new CarNotAvailableException(reservation.getStartDate(), reservation.getEndDate());
      }

        //        CHECK IF PAYMENT ALREADY HAD BEEN COMPLETED, SO NO DOUBLE DATA GOES INTO DATABASE
        Optional<Payout> payoutOptional = payoutRepository.findPayoutByReservationNumber(reservationNumber);
        if (payoutOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment had already been completed");
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
                reservation.setPayment(paymentEnum);

                //          GETTER FOR UPDATING PAYMENT TABLE
                Customer customer = customerRepository.findById(car.getCustomerNumber()).orElseThrow(() -> new NotFoundException("Customer", car.getCustomerNumber()));

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
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Payment is already PAID and cannot be re-opened");
            }
            reservationRepository.save(reservation);
        }
        return reservation;
    }

    public void deleteReservation(Long reservationNumber) {
        Reservation reservation = reservationRepository.findReservationByReservationNumber(reservationNumber).
                orElseThrow(() -> new NotFoundException("Reservation number", reservationNumber));
        reservationRepository.delete(reservation);
    }


    public Reservation getReservation(Long reservationNumber) {
        return reservationRepository.findById(reservationNumber).orElseThrow(()-> new NotFoundException("reservation", reservationNumber ));
    }

    public Optional<List<Reservation>> getReservations(Long customerNumber) {
        return reservationRepository.getReservations(customerNumber);
    }


    public Optional<List<Reservation>> getRentedCars(LocalDate startDate, LocalDate endDate) {
        Optional<List<Reservation>> licenses = reservationRepository.getRentedCars(startDate, endDate);
        if (licenses.isEmpty()){
            throw new NotFoundException("Reservations");
        } else {return licenses;}
    }


    public Long addNewReservation(Long customerNumber, String licensePlate, Integer kmPackage, LocalDate startDate, LocalDate endDate, Double rent, double packagePrice, PaymentEnum paymentEnum) {

        double daysRent = Period.between(startDate, endDate).getDays();

        if (daysRent == 0) {
            daysRent = 1;
        }

        Reservation newReservation = new Reservation();
        newReservation.setCustomerNumber(customerNumber);
        newReservation.setLicensePlate(licensePlate);
        newReservation.setKmPackage(kmPackage);
        newReservation.setStartDate(startDate);
        newReservation.setEndDate(endDate);
        newReservation.setRent((rent * daysRent) + packagePrice);
        newReservation.setPackagePrice(packagePrice);
        newReservation.setPayment(paymentEnum);

        reservationRepository.save(newReservation);

        if (newReservation.getPayment() == PaymentEnum.PAID) {
            Car car = carRepository.findCarByLicensePlate(newReservation.getLicensePlate()).orElseThrow(() -> new NotFoundException("Reservation", newReservation.getReservationNumber()));
            Customer customer = customerRepository.getById(car.getCustomerNumber());

            double sharityProfit = NumberRounder.roundDouble((newReservation.getRent() * 0.1), 2);
            double tax = NumberRounder.roundDouble((newReservation.getRent() * 0.21), 2);
            double payoutAmount = NumberRounder.roundDouble((newReservation.getRent() - sharityProfit - tax), 2);

            //        SETTERS TO SET THE PAYOUT AND WRITE ON BALANCE OF OWNER
            Payout payout = new Payout(newReservation.getReservationNumber(), sharityProfit, payoutAmount, tax, customer.getCustomerNumber());
            payout.setPayoutAmount(payoutAmount);
            payout.setSharityProfit(sharityProfit);
            payout.setTax(tax);
            payoutRepository.save(payout);

            customer.setBalance(customer.getBalance() + payoutAmount);
            customerRepository.save(customer);
        }
        return newReservation.getReservationNumber();

    }
}