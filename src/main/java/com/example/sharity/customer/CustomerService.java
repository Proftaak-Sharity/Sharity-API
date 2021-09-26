// The service creates the service layer of our application. It connects the Controller with the Data Access layer
package com.example.sharity.customer;

import com.example.sharity.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        customerRepository.save(customer);
    }



    public void deleteCustomer(Long id) {
        customerRepository.findById(id);
            boolean exists = customerRepository.existsById(id);
            if (!exists) {
                throw new IllegalStateException("Customer with customer number " + id + " does not exists.");
            }
            customerRepository.deleteById(id);
    }


    @Transactional
    public void updateCustomer(Long id, String firstName, String email) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Customer with customer number " + id + " does not exists"));
        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(customer.getFirstName(), firstName)){
            customer.setFirstName(firstName);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(customer.getEmail(), email)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
            customer.setEmail(email);
        }
    }
}
