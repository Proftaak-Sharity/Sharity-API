package com.example.sharity.customer;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Table (name = "drivers_license")
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

    public DriversLicense(String licenseNumber, CountryEnum country, LocalDate validUntil) {
        this.licenseNumber = licenseNumber;
        this.country = country;
        this.validUntil = validUntil;
    }

    public DriversLicense(String licenseNumber, CountryEnum country, LocalDate validUntil, Long customerNumber) {
        this.licenseNumber = licenseNumber;
        this.country = country;
        this.validUntil = validUntil;
        this.customerNumber =  customerNumber;
    }


}
