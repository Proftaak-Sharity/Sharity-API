package com.example.sharity.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public class CarModel extends BaseModel {

    @Column(unique = true)
    private String licensePlate;

    @Column
    private String make;

    @Column
    private String model;


}
