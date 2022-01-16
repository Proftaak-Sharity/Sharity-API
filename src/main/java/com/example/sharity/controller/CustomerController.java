// The controller creates the API layer of our application. Basically it let us connect to localhost:8080/api/customer
package com.example.sharity.controller;

import com.example.sharity.customer.*;
import com.example.sharity.exception.*;
import com.example.sharity.repository.CustomerImageRepository;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.repository.DriversLicenseRepository;
import com.example.sharity.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final EmailValidator emailValidator;
    private final DriversLicenseRepository driversLicenseRepository;
    private final CustomerImageRepository customerImageRepository;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository, EmailValidator emailValidator, DriversLicenseRepository driversLicenseRepository, CustomerImageRepository customerImageRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.emailValidator = emailValidator;
        this.driversLicenseRepository = driversLicenseRepository;
        this.customerImageRepository = customerImageRepository;
    }

//    ************** CUSTOMERS ***************

    @GetMapping(path = "{customerNumber}")
    public Customer getCustomer(@PathVariable("customerNumber") Long customerNumber) {

        return customerRepository.findById(customerNumber).orElseThrow(() -> new NotFoundException("Customer number", customerNumber));
    }

    @PostMapping
    public void addCustomer(
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
    }

    //    UPDATE SELECTED DATA FROM DATABASE
    @PutMapping(path = "/update")
    public void updateCustomer(
            @RequestParam Long customerNumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String houseNumber,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) CountryEnum country,
            @RequestParam(required = false) @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate dateOfBirth,
            @RequestParam(required = false) String phoneNumber) throws NoSuchAlgorithmException {

        customerService.updateCustomer(customerNumber, firstName, lastName, dateOfBirth, address, houseNumber, postalCode, city, country, phoneNumber);
    }

    @GetMapping(path = {"/login"})
    public Customer getUser(@RequestParam String email,
                               @RequestParam String password) {

        Customer customer = customerRepository
                .findCustomerByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email not found", email));

        if((Objects.equals(email, customer.getEmail())) && (Objects.equals(password, customer.getPassword()))) {
            return customer;
        }
        throw new NotFoundException("cred. not found", email);
    }

    @GetMapping(path = "/emailcheck")
    public Boolean checkEmail(@RequestParam String email) {

        if (emailValidator.patternMatches(email, "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new EmailPatternException(email);
        } else {
            return customerRepository.checkEmail(email);
        }
    }

    @PostMapping(path = "image")
    public void addCustomerImage(@RequestParam Long customerNumber,
                                 @RequestParam String image) {

        CustomerImage customerImage = new CustomerImage();
        customerImage.setCustomerNumber(customerNumber);
        customerImage.setImage(image);
        customerImageRepository.save(customerImage);
    }

    @GetMapping(path = "image/{customerNumber}")
    public CustomerImage getCustomerImage(@PathVariable Long customerNumber) {

        return customerImageRepository.findById(customerNumber).orElseThrow(()-> new NotFoundException("image", customerNumber));
    }


//    *************** DRIVERS LICENSE ***************

    @PostMapping(path = "/driverslicense")
    public void addDriversLicense(@RequestParam Long customerNumber,
                                  @RequestParam String licenseNumber,
                                  @RequestParam CountryEnum country,
                                  @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy")LocalDate validUntil) {
        DriversLicense driversLicense = new DriversLicense();
        driversLicense.setLicenseNumber(licenseNumber);
        driversLicense.setCustomerNumber(customerNumber);
        driversLicense.setValidUntil(validUntil);
        driversLicense.setCountry(country);

        driversLicenseRepository.save(driversLicense);
    }

    @GetMapping(path = "/driverslicense/check")
    public Boolean checkLicense(@RequestParam String licenseNumber) {

        return driversLicenseRepository.checkLicense(licenseNumber);
    }

    @GetMapping(path = {"/license/{customerNumber}"})
    public DriversLicense getDriversLicense(@PathVariable Long customerNumber) {

        return driversLicenseRepository.getDriversLicensesByCustomerNumber(customerNumber)
                .orElseThrow(()-> new NotFoundException("Customer not found", customerNumber));
    }







}





