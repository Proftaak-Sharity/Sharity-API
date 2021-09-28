package com.example.sharity.entity.customer;

import com.example.sharity.entity.car.Car;
import com.example.sharity.models.PersonModel;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer extends PersonModel {

    private String address;

    private LocalDate dateOfBirth;

    private String city;

    @OneToMany(targetEntity = Car.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public List<Car> cars;

    @OneToMany(targetEntity = BankAccount.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<BankAccount> bankaccounts;

}

