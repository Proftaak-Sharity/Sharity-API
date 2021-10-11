package com.example.sharity.service;

import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.exception.*;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.entity.customer.PasswordGenerator;
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

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordGenerator passwordGenerator) {
        this.customerRepository = customerRepository;
        this.passwordGenerator = passwordGenerator;
    }


    public Optional<Customer> findCustomer(Long customerNumber) {
        return customerRepository.findCustomerByCustomerNumber(customerNumber);
    }


    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }


    public void addCustomer(Customer customer) throws NoSuchAlgorithmException {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new NotUniqueException("Email");
        }

        customer.setPassword(passwordGenerator.getSHA512Password(customer.getPassword(), passwordGenerator.getSalt()));
        customerRepository.save(customer);
    }


    public void updateCustomer(Long customerNumber, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, String postalCode, CountryEnum countryEnum, String phoneNumber) throws NoSuchAlgorithmException {
        Customer customer = customerRepository.getById(customerNumber);
        Optional<Customer> emailOptional = customerRepository.findCustomerByEmail(email);

            if (firstName != null) {
                if (firstName.length() == 0) {
                    throw new EmptyValueException("First name");
                }
                customer.setFirstName(firstName);
            }

            if (lastName != null) {
                if (lastName.length() == 0) {
                    throw new EmptyValueException("Last name");
                }
                customer.setLastName(lastName);
            }

            if (email != null) {
                if (email.length() == 0) {
                    throw new EmptyValueException("Email");
                } else if (emailOptional.isPresent()) {
                    throw new NotUniqueException(email);
                }
                customer.setEmail(email);
            }

            if (password != null) {
                if (password.length() == 0) {
                    throw new EmptyValueException("Password");
                }
                customer.setPassword(passwordGenerator.getSHA512Password(customer.getPassword(), passwordGenerator.getSalt()));
            }

            if (dateOfBirth != null) {
                customer.setDateOfBirth(dateOfBirth);
            }

            if (address != null) {
                if (address.length() == 0) {
                    throw new EmptyValueException("Address");
                }
                customer.setAddress(address);
            }

            if (houseNumber != null) {
                if (houseNumber.length() == 0) {
                    throw new EmptyValueException("House number");
                }
                customer.setHouseNumber(houseNumber);
            }

            if (city != null) {
                if (city.length() == 0) {
                    throw new EmptyValueException("City");
                }
                customer.setCity(city);
            }

            if (postalCode != null) {
                if (postalCode.length() == 0) {
                    throw new EmptyValueException("Postal code");
                }
                customer.setPostalCode(postalCode);
            }

            if (countryEnum != null) {
                customer.setCountry(countryEnum);
            }

            if (phoneNumber != null) {
                if (phoneNumber.length() == 0) {
                    throw new EmptyValueException("Phone number");
                }
                customer.setPhoneNumber(phoneNumber);
            }

        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerNumber){
        customerRepository.deleteById(customerNumber);
    }
}
