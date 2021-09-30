package com.example.sharity.entity.car;

import com.example.sharity.entity.car.makeEnum;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
@MappedSuperclass
public class CarModel {

    @Id
    @Column(unique = true, length = 20)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private makeEnum make;

    private String model;


}
