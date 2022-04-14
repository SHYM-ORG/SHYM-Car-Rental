package com.shym.backend.service;

import com.shym.backend.model.Car;
import com.shym.backend.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car) {
        Optional<Car> dbCar = carRepository.findByModelAndColorAndSeries(car.getModel(), car.getColor(), car.getSeries());
        if (dbCar.isPresent()) {
            return dbCar.get();
        }
        return carRepository.save(car);
    }
}