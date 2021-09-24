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
            Optional<Customer> rob = CustomerRepository.findCustomerByEmail();
            ContactForm MailError = new ContactForm(
                    rob,
                    LocalDate.of(2021, Month.SEPTEMBER, 24),
                    "Could not cancel my reservations\nHow can i contact the owner of the vehicle",
            );

            Customer Bart = new Customer(
                    "Grootoonk",
                    "Bart",
                    LocalDate.of(1982, Month.APRIL, 3),
                    "b.grootoonk@student.avans.nl",
                    new BankAccount("NL26RABO126024369", "B GROOTOONK")
            );

            Customer Daniel = new Customer(
                    "Jansen",
                    "Daniël",
                    LocalDate.of(1979, Month.JANUARY, 27),
                    "d.jansen@student.avans.nl",
                    new BankAccount("NL12ABNA633256854", "D JANSEN")
            );

            repository.saveAll(
                    List.of(Rob, Bart, Daniel)
            );
        };
    }
}