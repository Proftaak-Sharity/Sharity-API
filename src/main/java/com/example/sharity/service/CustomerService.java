package com.example.sharity.service;

import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (((Optional<?>) customerOptional).isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Long customerNumber, String firstName, String lastName) {
        Customer customer = customerRepository.findById(customerNumber).orElseThrow(() -> new IllegalStateException("Customer with customer number " + customerNumber + " does not exist"));

        if (firstName.length() == 0) {
            throw new IllegalStateException("Firstname was empty");
        } else if (firstName.equals(customer.getFirstName())) {
            throw new IllegalStateException("This name is already your set name");
        }
    }
}
