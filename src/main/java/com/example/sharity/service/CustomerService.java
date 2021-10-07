package com.example.sharity.service;

import com.example.sharity.abstracts.EmailValidator;
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


    public Optional<Customer> findCustomer(Long customerNumber) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByCustomerNumber(customerNumber);

        if (customerOptional.isEmpty()) {
            throw new IllegalStateException("Customer with customer number " + customerNumber + " is unknown");
        }

        return customerRepository.findCustomerByCustomerNumber(customerNumber);
    }


    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    
    public void addCustomer(Customer customer) throws NoSuchAlgorithmException {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Email address " + customer.getEmail() + " already taken");
        } else if (customer.getEmail() == null) {
            throw new IllegalStateException("Email address entry is required");
        } else if (!EmailValidator.patternMatches(customer.getEmail(), "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new IllegalStateException("Email address does not meet set requirements. Did you miss a '@' or a '.' ?");
        }

        if (customer.getFirstName() == null) {
            throw new IllegalStateException("Firstname entry is required");
        } else if (customer.getLastName() == null) {
            throw new IllegalStateException("Lastname entry is required");
        } else if (customer.getPassword() == null) {
            throw new IllegalStateException("Password entry is required");
        } else if (customer.getAddress() == null) {
            throw new IllegalStateException("Address entry is required");
        } else if (customer.getHouseNumber() == null) {
            throw new IllegalStateException("House number entry is required");
        } else if (customer.getPostalCode() == null) {
            throw new IllegalStateException("Postal code entry is required");
        } else if (customer.getCity() == null) {
            throw new IllegalStateException("City entry is required");
        } else if (customer.getDateOfBirth() == null) {
            throw new IllegalStateException("Date of birth entry is required");
        } else if (customer.getCountry() == null) {
            throw new IllegalStateException("County entry is required");
        } else if (customer.getPhoneNumber() == null) {
            throw new IllegalStateException("Phone number entry is required");
        }

        customer.setPassword(PasswordGenerator.getSHA512Password(customer.getPassword(), PasswordGenerator.getSalt()));
        customerRepository.save(customer);
    }


    @Transactional
    public void updateCustomer(Long customerNumber, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, String postalCode, CountryEnum countryEnum, String phoneNumber) throws NoSuchAlgorithmException {
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

            if (email.equals(customer.getEmail())) {
                throw new IllegalStateException(email + " was already your set email");
            } else if (emailOptional.isPresent()) {
                throw new IllegalStateException("email address " + email + " is already taken");
            } else if (!EmailValidator.patternMatches(customer.getEmail(), "^[a-zA-Z0-9_+&*-]+(?:\\[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                throw new IllegalStateException("Email address does not meet set requirements. Did you miss a '@' or a '.' ?");
            }

            customer.setEmail(email);
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

        if (phoneNumber != null) {
            if (phoneNumber.equals(customer.getPhoneNumber())) {
                throw new IllegalStateException(phoneNumber + " was already set as your phone number");
            } else {
                customer.setPhoneNumber(phoneNumber);
            }
        }
    }


}