package com.shym.backend.model;

import com.shym.backend.enumeration.CarCategory;
import com.shym.backend.enumeration.CarModel;
import com.shym.backend.enumeration.Color;
import com.shym.backend.enumeration.FuelType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Car {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",  strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /***
     * the model of the car
     */
    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "car's model must be provided")
    private CarModel model;

    /***
     * the category of the car (sport car, family car ...)
     */

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "car's category must be provided")
    private CarCategory category;

    /***
     * the color of the car
     */

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    /***
     * the series of the car
     */

    @Column(name = "series")
    @NotNull(message = "car's series must be provided")
    private String series;

    /***
     * the fuel type of the car (diesel or gasoline)
     */

    @Column(name = "fuelType")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    /***
     * the consumption rate of the car in the city (in liters per 100 kilometer)
     */

    @Column(name = "consumptionRateCity")
    private float consumptionRateCity;

    /***
     * the consumption rate of the car in the Highway (in liters per 100 kilometer)
     */

    @Column(name = "consumptionRateHighway")
    private float consumptionRateHighway;

    public Car(CarModel model, CarCategory category, Color color, String series, FuelType fuelType, float consumptionRateCity, float consumptionRateHighway) {
        this.model = model;
        this.category = category;
        this.color = color;
        this.series = series;
        this.fuelType = fuelType;
        this.consumptionRateCity = consumptionRateCity;
        this.consumptionRateHighway = consumptionRateHighway;
    }

    public Car() {}

    public String getId() {
        return id;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public float getConsumptionRateCity() {
        return consumptionRateCity;
    }

    public void setConsumptionRateCity(float consummationRate) {
        this.consumptionRateCity = consummationRate;
    }

    public float getConsumptionRateHighway() {
        return consumptionRateHighway;
    }

    public void setConsumptionRateHighway(float consumptionRateHighway) {
        this.consumptionRateHighway = consumptionRateHighway;
    }
}
