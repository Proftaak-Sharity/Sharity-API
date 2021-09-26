package com.example.sharity.admin;

import com.example.sharity.customer.BankAccount;
import com.example.sharity.customer.Customer;
import com.example.sharity.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Configuration
public class ContactFormConfig {

    @Bean
    CommandLineRunner commandLineRunnerContactForm(ContactFormRepository repository) {
        return args -> {
            Customer Henk = new Customer(
                    "de Jong",
                    "Henk",
                    LocalDate.of(1983, Month.AUGUST, 29),
                    "h.dejong@student.avans.nl",
                    new BankAccount("NL71ABNA082343513", "H. de Jong")
            );

            ContactForm test = new ContactForm( Henk,
                    LocalDate.of(2021, Month.SEPTEMBER, 26),
                    "Stuff is broken joh");



                    repository.save(test);

//
//            CustomerRepository customerRepository = null;
//            Optional<Customer> bart = customerRepository.findCustomerByEmail("b.grootoonk@student.avans.nl");
////            ContactForm mailError = new ContactForm(
////                    bart,
////                    LocalDate.of(2021, Month.SEPTEMBER, 24),
////                    "Could not cancel my reservations\nHow can i contact the owner of the vehicle",
////            );
//            System.out.println(bart);
////           repository.saveAll(
////                    List.of(mailError)
////            );
        };
    }
}