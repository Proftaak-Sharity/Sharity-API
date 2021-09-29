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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CustomerNumber;

    private String address;

    private LocalDate dateOfBirth;

    private String city;

    @OneToMany(targetEntity = Car.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerNumber", referencedColumnName = "CustomerNumber")
    public List<Car> cars;

    @OneToMany(targetEntity = Bankaccount.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerNumber", referencedColumnName = "CustomerNumber")
    private List<Bankaccount> bankaccounts;

}

