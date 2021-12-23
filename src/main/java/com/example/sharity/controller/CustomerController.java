// The controller creates the API layer of our application. Basically it let us connect to localhost:8080/api/customer
package com.example.sharity.controller;

import com.example.sharity.customer.*;
import com.example.sharity.exception.*;
import com.example.sharity.exception.car.DeletedException;
import com.example.sharity.exception.UpdatedException;
import com.example.sharity.repository.BankaccountRepository;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.repository.DriversLicenseRepository;
import com.example.sharity.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final EmailValidator emailValidator;
    private final BankaccountRepository bankaccountRepository;
    private final DriversLicenseRepository driversLicenseRepository;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository, EmailValidator emailValidator,BankaccountRepository bankaccountRepository, DriversLicenseRepository driversLicenseRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.emailValidator = emailValidator;
        this.bankaccountRepository = bankaccountRepository;
        this.driversLicenseRepository = driversLicenseRepository;
    }

//    ************** CUSTOMERS ***************

    @GetMapping
    public List<Customer> findCustomers() {
        return customerService.findCustomers();
    }

    @GetMapping(path = "/emailcheck")
    public Boolean checkEmail(@RequestParam String email) {

        if (emailValidator.patternMatches(email, "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new EmailPatternException(email);
        } else {
            return customerRepository.checkEmail(email);
        }
    }


    @GetMapping(path = "{customerNumber}")
        public Customer getCustomer(
                @PathVariable("customerNumber") Long customerNumber) {

        return customerRepository.findById(customerNumber).orElseThrow(() -> new NotFoundException("Customer number", customerNumber));
    }

    @GetMapping(path = {"/login"})
    public Customer checkLogin(@RequestParam String email,
                               @RequestParam String password) {

        Customer customer = customerRepository
                .findCustomerByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email not found", email));

        if((Objects.equals(email, customer.getEmail())) && (Objects.equals(password, customer.getPassword()))) {
            return customer;
        }
        throw new NotFoundException("cred. not found", email);
    }

    @PostMapping
    public Long addCustomer(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate dateOfBirth,
            @RequestParam String address,
            @RequestParam String houseNumber,
            @RequestParam String postalCode,
            @RequestParam String city,
            @RequestParam String phoneNumber,
            @RequestParam CountryEnum country) throws NoSuchAlgorithmException {

        customerService.addCustomer(firstName, lastName, email, password, dateOfBirth, address, houseNumber, postalCode, city, phoneNumber, country);

        Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow(()-> new NotFoundException("email", email));

        return customer.getCustomerNumber();
    }

    //    UPDATE SELECTED DATA FROM DATABASE
    @PutMapping(path = "{customerNumber}")
    public void updateCustomer(
            @PathVariable("customerNumber") Long customerNumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate dateOfBirth,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String houseNumber,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) CountryEnum countryEnum) throws NoSuchAlgorithmException {

        Customer customer = customerRepository.findById(customerNumber).orElseThrow(() -> new NotFoundException("Customer number", customerNumber));

//            EXCEPTIONS
        if (firstName == null && lastName == null && email == null && password == null && dateOfBirth == null && address == null && houseNumber == null && city == null && postalCode == null && countryEnum == null && phoneNumber == null) {
            throw new AllNullException();
        } else if (email != null) {
            Optional<Customer> emailOptional = customerRepository.findCustomerByEmail(email);
            if (emailValidator.patternMatches(email, "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                throw new EmailPatternException(email);
            } else if (emailOptional.isPresent()) {
                throw new NotUniqueException(email);
            } else if (email.length() == 0) {
                throw new EmptyValueException("Email");
            }
        } else if (firstName != null && firstName.length() == 0) {
            throw new EmptyValueException("First name");
        } else if (lastName != null && lastName.length() == 0) {
            throw new EmptyValueException("Last name");
        } else if (password != null && password.length() == 0) {
            throw new EmptyValueException("Password");
        } else if (dateOfBirth != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date of birth cannot be empty");
        } else if (address != null && address.length() == 0) {
            throw new EmptyValueException("Address");
        } else if (houseNumber != null && houseNumber.length() == 0) {
            throw new EmptyValueException("House number");
        } else if (city != null && city.length() == 0) {
            throw new EmptyValueException("City");
        } else if (postalCode != null && postalCode.length() == 0) {
            throw new EmptyValueException("Postal code");
        } else if (countryEnum != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country cannot be empty");
        } else if (phoneNumber != null && phoneNumber.length() == 0) {
            throw new EmptyValueException("Phone number");
        }

        customerService.updateCustomer(customerNumber, firstName, lastName, email, password, dateOfBirth, address, houseNumber, postalCode, city, countryEnum, phoneNumber);
        throw new UpdatedException("Customer");
    }

    @DeleteMapping(path = "{customerNumber}")
    public void deleteCustomer(
            @PathVariable("customerNumber") Long customerNumber) {
        Customer customer = customerRepository.findById(customerNumber).orElseThrow(() -> new NotFoundException("Customer number", customerNumber));

        customerService.deleteCustomer(customerNumber);
        throw new DeletedException("Customer");
    }





//    *************** DRIVERS LICENSE ***************

    @GetMapping(path = {"/license/{customerNumber}"})
    public DriversLicense getDriversLicense(@PathVariable Long customerNumber) {

        return driversLicenseRepository.getDriversLicensesByCustomerNumber(customerNumber)
                .orElseThrow(()-> new NotFoundException("Customer not found", customerNumber));
    }

    @GetMapping(path = "/driverslicense/check")
    public Boolean checkLicense(@RequestParam String licenseNumber) {

        return driversLicenseRepository.checkLicense(licenseNumber);
    }

    @PostMapping(path = "/driverslicense")
    public void addDriversLicense(@RequestParam Long customerNumber,
                                  @RequestParam String licenseNumber,
                                  @RequestParam CountryEnum country,
                                  @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy")LocalDate validUntil) {
        customerService.addDriversLicense(customerNumber, licenseNumber, country, validUntil);
    }




//    ***************** BANK ACCOUNTS ****************

    @GetMapping(path = "/bankaccounts/{customerNumber}")
    public List<Bankaccount> getBankaccounts(@PathVariable Long customerNumber) {
        return customerService.findBankaccounts(customerNumber);
    }

    @GetMapping(path = "/bankaccounts/account/{id}")
    public Bankaccount getBankaccount(@PathVariable("id") Long id) {

        return bankaccountRepository.getBankaccountById(id).orElseThrow(()-> new NotFoundException("Bankaccount", id));
    }


    @PostMapping(path = "/bankaccounts/add")
    public void addBankaccount(@RequestParam Long customerNumber,
                               @RequestParam String iban,
                               @RequestParam String accountHolder) {

        Optional<Bankaccount> bankaccountOptional = bankaccountRepository.getBankaccountByIban(iban);

        if (bankaccountOptional.isPresent()) {
            throw new NotUniqueException("Bankaccount");
        } else {
            customerService.addBankaccount(customerNumber, iban, accountHolder);
            throw new CreatedException("Bankaccount");
        }
    }

    @PutMapping(path = "/bankaccounts/edit/{id}")
    public void editBankaccount(@PathVariable("id") Long id,
                                @RequestParam String iban,
                                @RequestParam String accountHolder) {
        customerService.editById(id, iban, accountHolder);
    }

    @DeleteMapping(path = "/bankaccounts/delete/{id}")
    public void deleteBankaccount(
            @PathVariable("id") Long id) {

        customerService.deleteBankaccount(id);
    }


}



