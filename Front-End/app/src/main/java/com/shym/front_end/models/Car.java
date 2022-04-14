package com.shym.front_end.models;


public class Car {
    private String place;
    private String model;
    private String image;

    public Car(String place, String model, String image) {
        this.place = place;
        this.model = model;
        this.image = image;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image_drawable) {
        this.image = image_drawable;
    }
}
