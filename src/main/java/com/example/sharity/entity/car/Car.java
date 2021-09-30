package com.example.sharity.entity.car;


import com.example.sharity.models.CarModel;
import com.example.sharity.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


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