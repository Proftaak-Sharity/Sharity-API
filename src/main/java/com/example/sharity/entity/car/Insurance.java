package com.example.sharity.entity.car;

import com.example.sharity.entity.car.enums.Coverage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
public class Insurance {

    @Id
    @Column(length = 30)
    private String insuranceNumber;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String insuranceCompany;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Coverage coverage;

    @Column(nullable = false)
    private LocalDate validUntil;

    public Insurance(String insuranceNumber, String licensePlate, String insuranceCompany, Coverage coverage, LocalDate validUntil) {
        this.insuranceNumber = insuranceNumber;
        this.licensePlate = licensePlate;
        this.insuranceCompany = insuranceCompany;
        this.coverage = coverage;
        this.validUntil = validUntil;
    }

    public Insurance() {
    }
}
