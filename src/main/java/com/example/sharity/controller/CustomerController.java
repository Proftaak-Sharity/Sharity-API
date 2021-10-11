// The controller creates the API layer of our application. Basically it let us connect to localhost:8080/api/customer
package com.example.sharity.controller;

import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.EmailValidator;
import com.example.sharity.exception.*;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.service.CustomerService;
import com.example.sharity.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final EmailValidator emailValidator;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository, EmailValidator emailValidator) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.emailValidator = emailValidator;
    }

//    GET ALL DATA FROM CUSTOMERTABLE
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

//    GET SPECIFIC DATA FROM CUSTOMERTABLE (BY CUSTOMERNUMBER)
    @GetMapping(path = "{customerNumber}")
        public Optional<Customer> findCustomer(
                @PathVariable("customerNumber") Long customerNumber,
                @Valid @RequestBody Customer customerDetails) {
                    Customer customer = customerRepository.findById(customerNumber).orElseThrow(() -> new NotFoundException("Customer number", customerNumber));

        return customerService.findCustomer(customerNumber);
    }

//    ADD CUSTOMERDATA TO DATABASE
    @PostMapping
    public void addCustomer(@RequestBody Customer customer) throws NoSuchAlgorithmException {

//        REQUIRED FIELD EXCEPTIONS
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
        } else if (customer.getEmail() == null) {
            throw new FieldRequiredException("Email");
        } else if (emailValidator.patternMatches(customer.getEmail(), "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new EmailPatternException(customer.getEmail());
        }
        customerService.addCustomer(customer);
        throw new CrudException("Customer", "adde");
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
            @RequestParam(required = false) CountryEnum countryEnum,
            @Valid @RequestBody Customer customerDetails) throws NoSuchAlgorithmException {
            Customer customer = customerRepository.findById(customerNumber).orElseThrow(() -> new NotFoundException("Customer number", customerNumber));

//            EXCEPTIONS
        if (firstName == null && lastName == null && email == null && password == null && dateOfBirth == null && address == null && houseNumber == null && city == null && postalCode == null && countryEnum == null && phoneNumber == null) {
            throw new AllNullException();
        } else if (email != null && emailValidator.patternMatches(email, "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new EmailPatternException(email);
        }

        customerService.updateCustomer(customerNumber, firstName, lastName, email, password, dateOfBirth, address, houseNumber, postalCode, city, countryEnum, phoneNumber);
        throw new CrudException("Customer", "update");
    }

    //  IF NO CUSTOMERNUMBER INSERTED
    @PutMapping
    public void updateAllCustomers(){
        throw new CrudAllException("update", "customers");
    }

    @DeleteMapping(path = "{customerNumber}")
    public void deleteCustomer(
            @PathVariable("customerNumber") Long customerNumber,
            @Valid @RequestBody Customer customerDetails) {
                Customer customer = customerRepository.findById(customerNumber).orElseThrow(() -> new NotFoundException("Customer number", customerNumber));

        customerService.deleteCustomer(customerNumber);
        throw new CrudException("Customer", "delete");
    }

    //  IF NO CUSTOMERNUMBER INSERTED
    @DeleteMapping
    public void deleteAllCustomers() {
        throw new CrudAllException("delete", "customers");
    }

}



