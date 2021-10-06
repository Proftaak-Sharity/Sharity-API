package com.example.sharity.service;

import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.abstracts.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
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
    public void updateCustomer(Long customerNumber, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, String postalCode, CountryEnum countryEnum) throws NoSuchAlgorithmException {
        Customer customer = customerRepository.findCustomerByCustomerNumber(customerNumber).orElseThrow(() -> new IllegalStateException("Customer with customer number " + customerNumber + " does not exist"));

        if (firstName != null) {
            if (firstName.length() == 0) {
                throw new IllegalStateException("Firstname was empty");
            } else if (firstName.equals(customer.getFirstName())) {
                throw new IllegalStateException(firstName + " is already your set firstname");
            } else {
                customer.setFirstName(firstName);
            }
        }

        if (lastName != null) {
            if (lastName.length() == 0) {
                throw new IllegalStateException("Lastname was empty");
            } else if (lastName.equals(customer.getLastName())) {
                throw new IllegalStateException(lastName + " is already your lastname");
            } else {
                customer.setFirstName(lastName);
            }
        }

        if (email != null) {
            Optional<Customer> emailOptional = customerRepository.findCustomerByEmail(email);
            if (emailOptional.isPresent()) {
                throw new IllegalStateException("email " + email + " is already taken");
            }
            if (email.equals(customer.getEmail())) {
                throw new IllegalStateException(email + " was already your set email");
            } else {
                customer.setEmail(email);
            }
        }

        if (password != null) {
            customer.setPassword(PasswordGenerator.getSHA512Password(customer.getPassword(), PasswordGenerator.getSalt()));
        }

        if (dateOfBirth != null) {
            if (dateOfBirth.equals(customer.getDateOfBirth())) {
                throw new IllegalStateException(dateOfBirth + " was already set as your date of birth");
            } else {
                customer.setDateOfBirth(dateOfBirth);
            }
        }

        if (address != null) {
            if (address.equals(customer.getAddress())) {
                throw new IllegalStateException(address + " was already set as your address");
            } else {
                customer.setAddress(address);
            }
        }

        if (houseNumber != null) {
            if (houseNumber.equals(customer.getHouseNumber())) {
                throw new IllegalStateException(houseNumber + " was already set as your house number");
            } else {
                customer.setHouseNumber(houseNumber);
            }
        }

        if (city != null) {
            if (city.equals(customer.getCity())) {
                throw new IllegalStateException(city + " was already set as your city");
            } else {
                customer.setCity(city);
            }
        }

        if (postalCode != null) {
            if (postalCode.equals(customer.getEmail())) {
                throw new IllegalStateException(postalCode + " was already set as your postal code");
            } else {
                customer.setPostalCode(postalCode);
            }
        }

        if (countryEnum != null) {
            if (countryEnum.equals(customer.getCountry())) {
                throw new IllegalStateException(countryEnum + " was already set as your country");
            } else {
                customer.setCountry(countryEnum);
            }
        }
    }
}