package com.example.sharity.service;

import com.example.sharity.entity.customer.EmailValidator;
import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.error.*;
import com.example.sharity.repository.CustomerRepository;
import com.example.sharity.entity.customer.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EmailValidator emailValidator;
    private final PasswordGenerator passwordGenerator;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, EmailValidator emailValidator, PasswordGenerator passwordGenerator) {
        this.customerRepository = customerRepository;
        this.emailValidator = emailValidator;
        this.passwordGenerator = passwordGenerator;
    }



    public Optional<Customer> findCustomer(Long customerNumber) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByCustomerNumber(customerNumber);

        if (customerOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with customer number " + customerNumber + " is unknown");
        }
        return customerRepository.findCustomerByCustomerNumber(customerNumber);
    }


    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }


    public void addCustomer(Customer customer) throws NoSuchAlgorithmException {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new NotUniqueError("Email");
        } else if (customer.getEmail() == null) {
            throw new EmptyValueError("Email");
        } else if (emailValidator.patternMatches(customer.getEmail(), "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new EmailPatternError(customer.getEmail());
        }

        if (customer.getFirstName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Firstname entry is required");
        } else if (customer.getLastName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lastname entry is required");
        } else if (customer.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password entry is required");
        } else if (customer.getAddress() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address entry is required");
        } else if (customer.getHouseNumber() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "House number entry is required");
        } else if (customer.getPostalCode() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Postal code entry is required");
        } else if (customer.getCity() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City entry is required");
        } else if (customer.getDateOfBirth() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date of birth entry is required");
        } else if (customer.getCountry() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "County entry is required");
        } else if (customer.getPhoneNumber() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number entry is required");
        }

        customer.setPassword(passwordGenerator.getSHA512Password(customer.getPassword(), passwordGenerator.getSalt()));
        customerRepository.save(customer);
        throw new DataInserted("Customer");
    }


    public void updateCustomer(Long customerNumber, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, String postalCode, CountryEnum countryEnum, String phoneNumber) throws NoSuchAlgorithmException {
        Customer customer = customerRepository.getById(customerNumber);
        Optional<Customer> emailOptional = customerRepository.findCustomerByEmail(email);

        if (firstName == null && lastName == null && email == null && password == null && dateOfBirth == null && address == null && houseNumber == null && city == null && postalCode == null && countryEnum == null && phoneNumber == null) {
            throw new AllNullError();
        } else {

            if (firstName != null) {
                if (firstName.length() == 0) {
                    throw new EmptyValueError("first name");
                } else if (firstName.equals(customer.getFirstName())) {
                    throw new NoChangesStringError("First name", firstName);
                } else {
                    customer.setFirstName(firstName);
                }
            }

            if (lastName != null) {
                if (lastName.length() == 0) {
                    throw new EmptyValueError("last name");
                } else if (lastName.equals(customer.getLastName())) {
                    throw new NoChangesStringError("Last name", lastName);
                } else {
                    customer.setFirstName(lastName);
                }
            }

            if (email != null) {
                if (email.length() == 0) {
                    throw new EmptyValueError("email");
                } else if (email.equals(customer.getEmail())) {
                    throw new NoChangesStringError("email", email);
                } else if (emailOptional.isPresent()) {
                    throw new NotUniqueError(email);
                } else if (emailValidator.patternMatches(email, "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                    throw new EmailPatternError(email);
                } else {
                    customer.setEmail(email);
                }
            }

            if (password != null) {
                if (password.length() == 0) {
                    throw new EmptyValueError("password");
                } else {
                    customer.setPassword(passwordGenerator.getSHA512Password(customer.getPassword(), passwordGenerator.getSalt()));
                }
            }

            if (dateOfBirth != null) {
                if (dateOfBirth.equals(customer.getDateOfBirth())) {
                    throw new NoChangesDateError("Date of birth", dateOfBirth);
                } else {
                    customer.setDateOfBirth(dateOfBirth);
                }
            }

            if (address != null) {
                if (address.length() == 0) {
                    throw new EmptyValueError("address");
                } else if (address.equals(customer.getAddress())) {
                    throw new NoChangesStringError("Address", address);
                } else {
                    customer.setAddress(address);
                }
            }

            if (houseNumber != null) {
                if (password.length() == 0) {
                    throw new EmptyValueError("password");
                }
                if (houseNumber.equals(customer.getHouseNumber())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, houseNumber + " was already set as your house number");
                } else {
                    customer.setHouseNumber(houseNumber);
                }
            }

            if (city != null) {
                if (city.equals(customer.getCity())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, city + " was already set as your city");
                } else {
                    customer.setCity(city);
                }
            }

            if (postalCode != null) {
                if (postalCode.equals(customer.getEmail())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, postalCode + " was already set as your postal code");
                } else {
                    customer.setPostalCode(postalCode);
                }
            }

            if (countryEnum != null) {
                if (countryEnum.equals(customer.getCountry())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, countryEnum + " was already set as your country");
                } else {
                    customer.setCountry(countryEnum);
                }
            }

            if (phoneNumber != null) {
                if (phoneNumber.equals(customer.getPhoneNumber())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, phoneNumber + " was already set as your phone number");
                } else {
                    customer.setPhoneNumber(phoneNumber);
                }
            }
        }
        customerRepository.save(customer);
        throw new DataUpdated("Customer");
    }


    public void deleteCustomer(Long customerNumber){
        Optional<Customer> customerOptional = customerRepository.findCustomerByCustomerNumber(customerNumber);

        if (customerOptional.isPresent()) {
            customerRepository.deleteById(customerNumber);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with customer number " + customerNumber + " unknown");
        }
    }
}