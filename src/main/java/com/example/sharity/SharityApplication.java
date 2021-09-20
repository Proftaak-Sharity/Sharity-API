package com.example.sharity;

import com.example.sharity.car.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharityApplication.class, args);

        Car myCar = new Car("AA-01-AA", "Audi", "A8", 4, 5);

        System.out.println(myCar.toString());




    }


}
