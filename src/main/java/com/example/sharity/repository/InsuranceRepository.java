package com.example.sharity.repository;

import com.example.sharity.entity.car.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, String> {
}
