package com.example.sharity.entity.customer;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
public class DriversLicense {

    @Id
    @Column(length = 20)
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountryEnum country;

    @Column(nullable = false)
    private LocalDate validUntil;

    @Column(name = "license_copy_url")
    private String licenseCopyURL;

    public DriversLicense() {
    }

    public DriversLicense(String licenseNumber, CountryEnum country, LocalDate validUntil, String licenseCopyURL) {
        this.licenseNumber = licenseNumber;
        this.country = country;
        this.validUntil = validUntil;
        this.licenseCopyURL = licenseCopyURL;
    }
}
