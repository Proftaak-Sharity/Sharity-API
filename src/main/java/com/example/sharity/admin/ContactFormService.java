package com.example.sharity.admin;

import com.example.sharity.customer.Customer;
import com.example.sharity.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ContactFormService {

    private final ContactFormRepository customerRepository;

    @Autowired
    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return contactFormRepository.findAll();
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