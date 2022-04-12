package com.shym.backend.repository;

import com.shym.backend.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<String, Car> {
}
