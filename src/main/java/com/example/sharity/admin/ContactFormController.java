package com.example.sharity.admin;

import com.example.sharity.customer.Customer;
import com.example.sharity.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Requestmapping says that the api has to display the data in de path as noted
@RequestMapping(path = "api/contactform")
public class ContactFormController {

    private final CustomerService customerService;

    //  Autowired automagically connects this customerController to the @Service in customerService
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //    Everything in GetMapping is shown in the browser
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{customerNumber}")
    public void deleteCustomer(@PathVariable("customerNumber") Long customerNumber) {
        customerService.deleteCustomer(customerNumber);
    }

    @PutMapping(path = "{customerNumber}")
    public void updateCustomer(
            @PathVariable("customerNumber") Long customerNumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String email) {
        customerService.updateCustomer(customerNumber, firstName, email);
    }

}
