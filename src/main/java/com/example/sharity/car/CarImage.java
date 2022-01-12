package com.example.sharity.car;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
public class CarImage implements Serializable {

    @Id
    @Column(unique = true, length = 20, name = "license_plate")
    private String licensePlate;

    @Column(columnDefinition = "LONGBLOB")
    private String image;

    public CarImage(String licensePlate, String image) {
        this.licensePlate = licensePlate;
        this.image = image;
    }

    public CarImage() {
    }
}
