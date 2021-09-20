package com.example.sharity.car;


public class Car {

    String licensePlate;
    String make;
    String model;
    String bodyType;
    String gearbox;
    int enginePower;
    int yearOfBuild;
    int doors;
    String color;
    int seats;
    String damage;
    boolean available;
    double dailyRate;
    double pricePerKilometer;
    double packagePrice500;
    double getPackagePrice1000;

//    test
    int wheels;

    public Car(int wheels) {
        this.wheels = wheels;
    }

    public int amountWheels() {
        return this.wheels;
    }
}
