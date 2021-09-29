package com.example.sharity.entity.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
public class DriversLicense {

    @Id
    @Column(length = 20)
    private String licenseNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CountryEnum country;

    @Column(nullable = false)
    private LocalDate validUntil;

    @Column(name = "license_copy_url", nullable = false)
    private String licenseCopyURL;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerNumber", referencedColumnName = "CustomerNumber")
    public Customer customerNumber;

}
