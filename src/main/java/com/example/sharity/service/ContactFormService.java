package com.example.sharity.service;

import com.example.sharity.entity.admin.ContactForm;
import com.example.sharity.repository.ContactFormRepository;
import com.example.sharity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;

    @Autowired
    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }


    public void addContactForm(ContactForm contactForm) {
        contactFormRepository.save(contactForm);
    }

    public List<ContactForm> getContactForms() {
        return contactFormRepository.findAll();
    }

    public void addNewContactForm(ContactForm contactForm) {
    }

    public void deleteContactForm(Long formNumber) {
    }


    // TODO:  gestolen van rob, maak hiervan update status naar afgehandeld
//    @Transactional
//    public void updateCustomer(Long customerNumber, String firstName, String lastName) {
//        ContactForm contactForm = contactFormRepository.findById(formNumber).orElseThrow(() -> new IllegalStateException("Customer with customer number " + customerNumber + " does not exist"));
//
//        if (firstName.length() == 0) {
//            throw new IllegalStateException("Firstname was empty");
//        } else if (firstName.equals(contactForm.getFirstName())) {
//            throw new IllegalStateException("This name is already your set name");
//        }
//    }
}
