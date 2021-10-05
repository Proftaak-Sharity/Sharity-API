// The controller creates the API layer of our application. Basically it let us connect to localhost:8080/api/customer
package com.example.sharity.controller;

import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.service.CustomerService;
import com.example.sharity.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

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
            @RequestParam(required = false) String dateOfBirth,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String houseNumber,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) CountryEnum countryEnum)

    {
        customerService.updateCustomer(customerNumber, firstName, lastName, email, password, dateOfBirth, address, houseNumber, city, countryEnum);
    }

}



