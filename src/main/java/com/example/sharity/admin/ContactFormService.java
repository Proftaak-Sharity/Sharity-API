package com.example.sharity.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


@Service
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;

    @Autowired
    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    @GetMapping
    public List<ContactForm> getContactForm() {
        return contactFormRepository.findAll();
    }

    public void addNewContactForm(ContactForm contactForm) {
        Optional<ContactForm> contactFormOptional = contactFormRepository.findContactFormById(contactForm.getFormNumber());
        if (contactFormOptional.isPresent()) {
            throw new IllegalStateException("ID already taken");
        }
        contactFormRepository.save(contactForm);
    }

    public void deleteContactForm(Long formNumber) {
        contactFormRepository.findById(formNumber);
        boolean exists = contactFormRepository.existsById(formNumber);
        if (!exists) {
            throw new IllegalStateException("Form with form number " + formNumber + " does not exists.");
        }
        contactFormRepository.deleteById(formNumber);
    }

    public Optional<ContactForm> getContactForms(Long formNumber) {
        contactFormRepository.findById(formNumber);
        boolean exists = contactFormRepository.existsById(formNumber);
        if (!exists) {
            throw new IllegalStateException("Form with form number " + formNumber + " does not exists.");
        }
        return contactFormRepository.findById(formNumber);
    }

//    public void updateContactForm(Long formNumber, String firstName, String email) {
//
//    }

//
//    @Transactional
//    public void updateCustomer(Long formNumber, String firstName, String email) {
//        Customer customer = customerRepository.findById(customerNumber)
//                .orElseThrow(() -> new IllegalStateException("Customer with customer number " + customerNumber + " does not exists"));
//        if (firstName != null &&
//                firstName.length() > 0 &&
//                !Objects.equals(customer.getFirstName(), firstName)){
//            customer.setFirstName(firstName);
//        }
//
//        if (email != null &&
//                email.length() > 0 &&
//                !Objects.equals(customer.getEmail(), email)) {
//            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
//            if (customerOptional.isPresent()) {
//                throw new IllegalStateException("Email already taken");
//            }
//            customer.setEmail(email);
//        }
    }
