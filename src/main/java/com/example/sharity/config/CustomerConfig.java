package com.example.sharity.config;

import com.example.sharity.entity.customer.Bankaccount;
import com.example.sharity.entity.customer.CountryEnum;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.BankaccountRepository;
import com.example.sharity.repository.CarRepository;
import com.example.sharity.repository.CustomerRepository;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.Month;

@Component
public class CustomerConfig {

    public CustomerConfig(
            CustomerRepository customerRepository,
            BankaccountRepository bankaccountRepository,
            CarRepository carRepository
    ) {
        Customer Rob = new Customer(
                "Rob",
                "van der Horst",
                 LocalDate.of(1983, Month.AUGUST, 29),
                "Hoofdstraat",
                "12",
                "Klundert",
                 CountryEnum.NETHERLANDS,
                new Bankaccount("NL12INGB122365432", "RPL VAN DER HORST")
        );
        customerRepository.save(Rob);
    }
}




//        'rob.vanderhorst@student.avans.nl', 'Rob', 'van der Horst', 'welkom01', 'Hoofdstraat', '12', 'Klundert', 'NETHERLANDS', '1983-08-29'),
//# (2, 'daniel.jansen@student.avans.nl', 'Daniël', 'Jansen', 'welkom02', 'Appelweg', '10', 'Made', 'LUXEMBOURG', '1978-03-20'),
//# (3, 'bart.grootoonk@student.avans.nl', 'Bart', 'Grootoonk', 'welkom03', 'Boompjesdijk', '48', 'Etten-Leur', 'NETHERLANDS', '1982-11-09'),
//# (4, 'joris.jansen@student.avans.nl', 'Joris', 'Jansen', 'welkom04', 'Marterweg', '45-B', 'Berkel-Enschot', 'NETHERLANDS', '1987-03-31'),
//# (5, 'lars.hanegraaf@student.avans.nl', 'Lars', 'Hanegraaf', 'welkom05', 'Vrijheid', '56', 'Breda', 'BELGIUM', '1985-06-04');
