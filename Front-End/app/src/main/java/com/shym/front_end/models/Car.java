package com.shym.front_end.models;

public class Car {

    private String idOffer;
    private String model;
    private String series;
    private int pricePerDay;
    private boolean availableNow;
    private String image;

    public Car(String idOffer, String model, String series, int pricePerDay, boolean availableNow, String image) {
        this.idOffer = idOffer;
        this.model = model;
        this.series = series;
        this.pricePerDay = pricePerDay;
        this.availableNow = availableNow;
        this.image = image;
    }

    public String getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(String idOffer) {
        this.idOffer = idOffer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailableNow() {
        return availableNow;
    }

    public void setAvailableNow(boolean availableNow) {
        this.availableNow = availableNow;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
