package com.example.sharity.service;

import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.abstracts.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
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

    public void addCustomer(Customer customer) throws NoSuchAlgorithmException {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("email already taken");
        }

        customer.setPassword(PasswordGenerator.getSHA512Password(customer.getPassword(), PasswordGenerator.getSalt()));
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Long customerNumber, String firstName, String lastName, String email, String password, String dateOfBirth, String address, String houseNumber, String city, CountryEnum countryEnum) {
        Customer customer = customerRepository.findCustomerByCustomerNumber(customerNumber).orElseThrow(() -> new IllegalStateException("Customer with customer number " + customerNumber + " does not exist"));

        if (firstName.length() == 0) {
            throw new IllegalStateException("Firstname was empty");
        } else if (firstName.equals(customer.getFirstName())) {
            throw new IllegalStateException("This name is already your set name");
        } else {
            customer.setFirstName(firstName);
        }

        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("email already taken");
        } else {
            customer.setEmail(email);
        }
    }
}
