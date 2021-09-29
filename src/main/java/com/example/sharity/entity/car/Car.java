package com.example.sharity.entity.car;


import com.example.sharity.models.CarModel;
import com.example.sharity.reservation.Reservation;
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

    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private List<Reservation> reservations;


}