package com.example.sharity;

import com.example.sharity.car.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharityApplication.class, args);

        Car myCar = new Car(4);

        System.out.println("My car has " + myCar.amountWheels() + " wheels!");


    }


}
