package com.shym.backend.repository;

import com.shym.backend.enumeration.CarModel;
import com.shym.backend.enumeration.Color;
import com.shym.backend.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, String> {
    public Optional<Car> findByModelAndColorAndSeries(CarModel model, Color color, String series);
}
