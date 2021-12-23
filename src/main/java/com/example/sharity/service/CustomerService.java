package com.example.sharity.service;

import com.example.sharity.customer.*;
import com.example.sharity.repository.BankaccountRepository;
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
    private final BankaccountRepository bankaccountRepository;
    private final DriversLicenseRepository driversLicenseRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordGenerator passwordGenerator, BankaccountRepository bankaccountRepository, DriversLicenseRepository driversLicenseRepository) {
        this.customerRepository = customerRepository;
        this.passwordGenerator = passwordGenerator;
        this.bankaccountRepository = bankaccountRepository;
        this.driversLicenseRepository = driversLicenseRepository;
    }


    public Customer getCustomer(Long customerNumber) {
        return customerRepository.getById(customerNumber);
    }


    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public List<Bankaccount> findBankaccounts(Long customerNumber) {
        return bankaccountRepository.findAll(customerNumber);
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


    public void updateCustomer(Long customerNumber, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String address, String houseNumber, String city, String postalCode, CountryEnum countryEnum, String phoneNumber) throws NoSuchAlgorithmException {
        Customer customer = customerRepository.getById(customerNumber);

        if (firstName != null) {
            customer.setFirstName(firstName);
        } if (lastName != null) {
            customer.setLastName(lastName);
        } if (email != null) {
            customer.setEmail(email);
        } if (password != null) {
            customer.setPassword(password);
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

    public void addBankaccount(Long customerNumber, String iban, String accountHolder) {
        Bankaccount newBankaccount = new Bankaccount();
        newBankaccount.setCustomerNumber(customerNumber);
        newBankaccount.setAccountHolder(accountHolder);
        newBankaccount.setIban(iban);
        bankaccountRepository.save(newBankaccount);
    }

    public void deleteBankaccount(Long id) { bankaccountRepository.deleteById(id);}

    public void editById(Long id, String iban, String accountHolder) {
        Bankaccount bankaccount = bankaccountRepository.getById(id);

        bankaccount.setIban(iban);

        if (accountHolder != null) {
            bankaccount.setAccountHolder(accountHolder);
        }
        bankaccountRepository.save(bankaccount);
    }

    public void addDriversLicense(Long customerNumber, String licenseNumber, CountryEnum country, LocalDate validUntil) {

        DriversLicense driversLicense = new DriversLicense();

        driversLicense.setCustomerNumber(customerNumber);
        driversLicense.setLicenseNumber(licenseNumber);
        driversLicense.setCountry(country);
        driversLicense.setValidUntil(validUntil);
        driversLicenseRepository.save(driversLicense);
    }

}
