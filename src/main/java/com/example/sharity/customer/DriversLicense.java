package com.example.sharity.customer;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity (name = "DriversLicense")
public class DriversLicense {

    @Id
    @Column(length = 20, nullable = false)
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountryEnum country;

    @Column(nullable = false)
    private LocalDate validUntil;

    private Long customerNumber;

    public DriversLicense() {
    }

    public DriversLicense(String licenseNumber, CountryEnum country, @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate validUntil, Long customerNumber) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.licenseNumber = licenseNumber;
        this.country = country;
        this.validUntil = validUntil;
        this.customerNumber =  customerNumber;
    }
}
