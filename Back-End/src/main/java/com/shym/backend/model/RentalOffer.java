package com.shym.backend.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class RentalOffer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",  strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /***
     * the price of renting the car for one day
     */
    @Column(name = "pricePerDay")
    @NotNull(message = "car's price per day must be provided")
    private int pricePerDay;

    /***
     * description of the car (can contain the state of the car, how much it has been used ...)
     */

    @Column(name = "description")
    private String description;

    /***
     * is the car available for rent right now or not
     */
    @Column(name = "availableNow")
    @NotNull
    private boolean availableNow;

    /***
     * the id of the car concerned by this offer
     */

    @ManyToOne
    @JoinColumn(name = "CarId")
    private Car car;

    /***
     * the id of the agency publishing the offer
     */

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agency;

    private String imagePath;

    public RentalOffer(int pricePerDay, String description, Car car, Agency agency) {
        this.pricePerDay = pricePerDay;
        this.description = description;
        this.car = car;
        this.agency = agency;
    }

    public RentalOffer() {}

    public String getId() {
        return id;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public boolean isAvailableNow() {
        return availableNow;
    }

    public void setAvailableNow(boolean availableNow) {
        this.availableNow = availableNow;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}