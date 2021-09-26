//package com.example.sharity.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
////Requestmapping says that the api has to display the data in de path as noted
//@RequestMapping(path = "api/contactform")
//public class ContactFormController {
//
//    private final ContactFormService contactFormService;
//
//    //  Autowired automagically connects this customerController to the @Service in customerService
//    @Autowired
//    public ContactFormController( ContactFormService contactFormService) {
//        this.contactFormService = contactFormService;
//    }
//
//    //    Everything in GetMapping is shown in the browser
//    @GetMapping
//    public Optional<ContactForm> getContactForms(@RequestBody Long formNumber) {
//        return contactFormService.getContactForms(formNumber);
//    }
//
//    @PostMapping
//    public void registerNewCustomer(@RequestBody ContactForm contactForm){
//        contactFormService.addNewContactForm(contactForm);
//    }
//
//    @DeleteMapping(path = "{formNumber}")
//    public void deleteContactForm(@PathVariable("formNumber") Long formNumber) {
//        contactFormService.deleteContactForm(formNumber);
//    }
//
////    @PutMapping(path = "{formNumber}")
////    public void updateCustomer(
////            @PathVariable("formNumber") Long formNumber,
////            @RequestParam(required = false) String firstName,
////            @RequestParam(required = false) String email) {
////        contactFormService.updateContactForm(formNumber, firstName, email);
////    }
//
//}
