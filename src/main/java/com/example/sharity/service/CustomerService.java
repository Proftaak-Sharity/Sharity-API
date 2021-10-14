package com.example.sharity.service;

import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.entity.customer.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordGenerator passwordGenerator;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordGenerator passwordGenerator) {
        this.customerRepository = customerRepository;
        this.passwordGenerator = passwordGenerator;
    }


    public Customer getCustomer(Long customerNumber) {
        return customerRepository.getById(customerNumber);
    }


    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }


    public void addCustomer(Customer customer) throws NoSuchAlgorithmException {
        customer.setPassword(passwordGenerator.getSHA512Password(customer.getPassword(), passwordGenerator.getSalt()));
        customerRepository.save(customer);
    }


    public void updateCustomer(Long customerNumber, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, String postalCode, CountryEnum countryEnum, String phoneNumber) throws NoSuchAlgorithmException {
        Customer customer = customerRepository.getById(customerNumber);

        if (firstName != null) {
            customer.setFirstName(firstName);
        } if (lastName != null) {
            customer.setLastName(lastName);
        } if (email != null) {
            customer.setEmail(email);
        } if (password != null) {
            customer.setPassword(passwordGenerator.getSHA512Password(customer.getPassword(), passwordGenerator.getSalt()));
        } if (dateOfBirth != null) {
            customer.setDateOfBirth(dateOfBirth);
        } if (address != null) {
            customer.setAddress(address);
        } if (houseNumber != null) {
            customer.setHouseNumber(houseNumber);
        }if (city != null) {
            customer.setCity(city);
        } if (postalCode != null) {
            customer.setPostalCode(postalCode);
        } if (countryEnum != null) {
            customer.setCountry(countryEnum);
        } if (phoneNumber != null) {
            customer.setPhoneNumber(phoneNumber);
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerNumber){
        customerRepository.deleteById(customerNumber);
    }
}
