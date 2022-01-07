package com.example.sharity.repository;

import com.example.sharity.car.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarImageRepository extends JpaRepository <CarImage, String> {

    
}
