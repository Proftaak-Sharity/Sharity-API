package com.example.sharity.reservation;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int reservationNumber;

    private double rent;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Reservation(double rent) {
        this.rent = rent;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public int getId() {
      return reservationNumber;
    }
}