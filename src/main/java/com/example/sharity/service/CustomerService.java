package com.example.sharity.service;

import com.example.sharity.customer.*;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.repository.DriversLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordGenerator passwordGenerator;
    private final DriversLicenseRepository driversLicenseRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordGenerator passwordGenerator, DriversLicenseRepository driversLicenseRepository) {
        this.customerRepository = customerRepository;
        this.passwordGenerator = passwordGenerator;
        this.driversLicenseRepository = driversLicenseRepository;
    }


    public Customer getCustomer(Long customerNumber) {
        return customerRepository.getById(customerNumber);
    }


    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer (String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String postalCode, String city, String phoneNumber, CountryEnum country)  throws NoSuchAlgorithmException {

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setPassword(password);
        newCustomer.setDateOfBirth(dateOfBirth);
        newCustomer.setAddress(address);
        newCustomer.setHouseNumber(houseNumber);
        newCustomer.setPostalCode(postalCode);
        newCustomer.setCity(city);
        newCustomer.setPhoneNumber(phoneNumber);
        newCustomer.setCountry(country);;
        customerRepository.save(newCustomer);

    }


    public void updateCustomer(Long customerNumber, String firstName, String lastName, LocalDate dateOfBirth, String address, String houseNumber, String postalCode, String city, CountryEnum country, String phoneNumber) throws NoSuchAlgorithmException {
        Customer customer = customerRepository.getById(customerNumber);

            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setDateOfBirth(dateOfBirth);
            customer.setAddress(address);
            customer.setHouseNumber(houseNumber);
            customer.setCity(city);
            customer.setPostalCode(postalCode);
            customer.setCountry(country);
            customer.setPhoneNumber(phoneNumber);

            customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerNumber){
        customerRepository.deleteById(customerNumber);
    }


}
