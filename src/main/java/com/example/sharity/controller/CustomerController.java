// The controller creates the API layer of our application. Basically it let us connect to localhost:8080/api/customer
package com.example.sharity.controller;

import com.example.sharity.dto.CustomerRequest;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.BankaccountRepository;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BankaccountRepository bankaccountRepository;

    @PostMapping(value = "/api/addCustomer")
    public Customer addCustomer(@RequestBody CustomerRequest request, Customer customer){
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(request.getCustomer().getEmail());
        if (((Optional<?>) customerOptional).isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        return customerRepository.save(request.getCustomer());
    }

    @GetMapping(value = "/api/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


}

