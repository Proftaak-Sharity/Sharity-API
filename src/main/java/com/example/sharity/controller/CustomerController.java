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
import org.aspectj.weaver.ast.Not;
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
    public void addCustomer(@RequestBody Customer customer) throws NoSuchAlgorithmException {
//        EXCEPTIONS
        if (customer.getCustomerNumber() != null) {
            throw new InputNotAllowedException("customer number(" + customer.getCustomerNumber() + ")");
        } else if (customer.getEmail() != null) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
            if (customerOptional.isPresent()) {
                throw new NotUniqueException("Email");
            } else if (emailValidator.patternMatches(customer.getEmail(), "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                throw new EmailPatternException(customer.getEmail());
            }
        } else {
            throw new FieldRequiredException("Email");
        }

        if (customer.getFirstName() == null) {
            throw new FieldRequiredException("First name");
        } else if (customer.getLastName() == null) {
            throw new FieldRequiredException("Last name");
        } else if (customer.getPassword() == null) {
            throw new FieldRequiredException("Password");
        } else if (customer.getAddress() == null) {
            throw new FieldRequiredException("Address");
        } else if (customer.getHouseNumber() == null) {
            throw new FieldRequiredException("House number");
        } else if (customer.getPostalCode() == null) {
            throw new FieldRequiredException("Postal Code");
        } else if (customer.getCity() == null) {
            throw new FieldRequiredException("City");
        } else if (customer.getDateOfBirth() == null) {
            throw new FieldRequiredException("Date of birth");
        } else if (customer.getCountry() == null) {
            throw new FieldRequiredException("Country");
        } else if (customer.getPhoneNumber() == null) {
            throw new FieldRequiredException("Phone number");
        }

        customerService.addCustomer(customer);
        throw new CreatedException("Customer");
    }

    //    UPDATE SELECTED DATA FROM DATABASE
    @PutMapping(path = "{customerNumber}")
    public void updateCustomer(
            @PathVariable("customerNumber") Long customerNumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
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






//    ***************** BANK ACCOUNTS ****************

    @GetMapping(path = "/bankaccounts/{customerNumber}")
    public List<Bankaccount> getBankaccounts(@PathVariable Long customerNumber) {
        return customerService.findBankaccounts(customerNumber);
    }

    @GetMapping(path = "/iban")
    public Bankaccount getBankaccount(@RequestParam String iban) {

        return bankaccountRepository.getBankaccountByIban(iban).orElseThrow(()-> new NotFoundException("Bankaccount", iban));
    }

    @PostMapping(path = "/bankaccounts")
    public void addBankaccount(@RequestParam Long customerNumber,
                               @RequestParam String iban,
                               @RequestParam String accountHolder) {

        Customer customer = customerRepository.findById(customerNumber).orElseThrow(()-> new NotFoundException("Customer number", customerNumber));
        Optional<Bankaccount> bankaccountOptional = bankaccountRepository.findById(iban);

        if (bankaccountOptional.isPresent()) {
            throw new NotUniqueException("Bankaccount");
        } else {
            customerService.addBankaccount(customerNumber, iban, accountHolder);
            throw new CreatedException("Bankaccount");
        }
    }

    @DeleteMapping(path = "/bankaccounts/{iban}")
    public void deleteBankaccount(
            @PathVariable("iban") String iban) {

        Bankaccount bankaccount = bankaccountRepository.findById(iban).orElseThrow(()-> new NotFoundException("Bank account"));
        customerService.deleteBankaccount(iban);
        throw new DeletedException("Bankaccount");
    }










}



