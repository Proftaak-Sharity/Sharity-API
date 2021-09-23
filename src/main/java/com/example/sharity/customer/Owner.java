package com.example.sharity.customer;

import com.example.sharity.car.Car;
import com.example.sharity.models.Person;


import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.OneToMany;
import javax.persistence.Table;

    @Entity
    @Table(name = "owners")
    public class Owner extends Person {

        @Column(name = "address")
        @NotEmpty
        private String address;

        @Column(name = "city")
        @NotEmpty
        private String city;

        @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
        private Set<Car> cars;

        public Owner() {
            super();
        }

        public Owner(String address, String city, Set<Car> cars) {
            this.address = address;
            this.city = city;
            this.cars = cars;
        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Set<Car> getCars() {
            return cars;
        }

        public void setCars(Set<Car> cars) {
            this.cars = cars;
        }

        @Override
        public String toString() {
            return "Owner{" +
                    "address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", cars=" + cars +
                    '}';
        }
    }

