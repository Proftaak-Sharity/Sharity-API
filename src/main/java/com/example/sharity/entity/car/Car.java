package com.example.sharity.entity.car;


import com.example.sharity.models.CarModel;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Car extends CarModel {

    @Enumerated(EnumType.STRING)
    private FuelTypeEnum fuelType;


}