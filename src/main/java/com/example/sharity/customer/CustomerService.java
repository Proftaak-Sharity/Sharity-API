// The service creates the service layer of our application. It connects the Controller with the Data Access layer
package com.example.sharity.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        customerRepository.save(customer);

    }

    public void deleteCustomer(Long customerNumber) {
        customerRepository.findById(customerNumber);
            boolean exists = customerRepository.existsById(customerNumber);
            if (!exists) {
                throw new IllegalStateException("Customer with customer number " + customerNumber + " does not exists.");
            }
            customerRepository.deleteById(customerNumber);
    }


    @Transactional
    public void updateCustomer(Long customerNumber, String firstName, String email) {
        Customer customer = customerRepository.findById(customerNumber)
                .orElseThrow(() -> new IllegalStateException("Customer with customer number " + customerNumber + " does not exists"));
        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(customer.getFirstName(), firstName)){
            customer.setFirstName(firstName);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(customer.getEmail(), email)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
            customer.setEmail(email);
        }
    }
}
