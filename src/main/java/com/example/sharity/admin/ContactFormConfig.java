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
    CommandLineRunner commandLineRunner(ContactFormRepository repository) {
        return args -> {
            Customer rob = customerRepository.findCustomerByEmail("b.grootoonk@student.avans.nl");
            ContactForm MailError = new ContactForm(
                    rob,
                    LocalDate.of(2021, Month.SEPTEMBER, 24),
                    "Could not cancel my reservations\nHow can i contact the owner of the vehicle",
            );

            repository.saveAll(
                    List.of(MailError)
            );
        };
    }
}