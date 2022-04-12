package com.shym.backend.model;

import com.shym.backend.enumeration.CarModel;
import com.shym.backend.enumeration.Color;
import com.shym.backend.enumeration.FuelType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    private CarModel model;

    /***
     * the category of the car (sport car, family car ...)
     */

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CarModel category;

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
    private String series;

    /***
     * the fuel type of the car (diesel or gasoline)
     */

    @Column(name = "fuelType")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    /***
     * the consummation rate of the car in liters per 100 kilometer
     */

    @Column(name = "consummationRate")
    private float consummationRate;

    public Car(CarModel model, CarModel category, Color color, String series, FuelType fuelType, float consummationRate) {
        this.model = model;
        this.category = category;
        this.color = color;
        this.series = series;
        this.fuelType = fuelType;
        this.consummationRate = consummationRate;
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

    public CarModel getCategory() {
        return category;
    }

    public void setCategory(CarModel category) {
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

    public float getConsummationRate() {
        return consummationRate;
    }

    public void setConsummationRate(float consummationRate) {
        this.consummationRate = consummationRate;
    }
}
