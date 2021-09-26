package com.example.sharity.admin;

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
    CommandLineRunner commandLineRunnerContacTForm(ContactFormRepository repository) {
        return args -> {
            CustomerRepository customerRepository = null;
            Optional<Customer> bart = customerRepository.findCustomerByEmail("b.grootoonk@student.avans.nl");
//            ContactForm mailError = new ContactForm(
//                    bart,
//                    LocalDate.of(2021, Month.SEPTEMBER, 24),
//                    "Could not cancel my reservations\nHow can i contact the owner of the vehicle",
//            );
            System.out.println(bart);
//           repository.saveAll(
//                    List.of(mailError)
//            );
        };
    }
}