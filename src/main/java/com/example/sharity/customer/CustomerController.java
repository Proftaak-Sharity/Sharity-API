// The controller creates the API layer of our application. Basically it let us connect to localhost:8080/api/customer
package com.example.sharity.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Restcontroller tells us that this is the controller. In this case, the controller of the customers
@RestController
//Requestmapping says that the api has to display the data in de path as noted
@RequestMapping(path = "api/customer")
public class CustomerController {

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
