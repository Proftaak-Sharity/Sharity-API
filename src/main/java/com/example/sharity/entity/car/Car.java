package com.example.sharity.entity.car;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
public class Car extends CarModel {

    @Enumerated(EnumType.STRING)
    private FuelTypeEnum fuelType;


}