package com.shym.backend.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    private int pricePerDay;

    /***
     * description of the car (can contain the state of the car, how much it has been used ...)
     */

    @Column(name = "description")
    private String description;

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
}