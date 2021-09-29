package com.example.sharity.entity.customer;

import com.example.sharity.entity.car.Car;
import com.example.sharity.models.PersonModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
public class Customer extends PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerNumber;

    private LocalDate dateOfBirth;
    private String address;
    private String houseNumber;
    private String city;

    @Enumerated(EnumType.STRING)
    private CountryEnum country;

    @JsonIgnore
    @OneToMany(targetEntity = Car.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerNumber", referencedColumnName = "CustomerNumber")
    public List<Car> cars;

    @JsonIgnore
    @OneToMany(targetEntity = BankAccount.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerNumber", referencedColumnName = "CustomerNumber")
    private List<BankAccount> bankaccounts;

    @JsonIgnore
    @OneToOne(mappedBy = "customerNumber")
    public DriversLicense driversLicense;

}

