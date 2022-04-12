package com.shym.backend.service;

import com.shym.backend.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
}
