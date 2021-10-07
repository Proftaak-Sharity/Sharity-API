// The controller creates the API layer of our application. Basically it let us connect to localhost:8080/api/customer
package com.example.sharity.controller;

import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.service.CustomerService;
import com.example.sharity.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    GET ALL DATA FROM CUSTOMERTABLE
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

//    GET SPECIFIC DATA FROM CUSTOMERTABLE (BY CUSTOMERNUMBER)
    @GetMapping(path = "{customerNumber}")
        public Optional<Customer> findCustomer(
                @PathVariable("customerNumber") Long customerNumber) {
        return customerService.findCustomer(customerNumber);
    }

//    ADD CUSTOMERDATA TO DATABASE
    @PostMapping
    public void addCustomer(@RequestBody Customer customer) throws NoSuchAlgorithmException {
        customerService.addCustomer(customer);
    }

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
        customerService.updateCustomer(customerNumber, firstName, lastName, email, password, dateOfBirth, address, houseNumber, postalCode, city, countryEnum, phoneNumber);
    }

}



