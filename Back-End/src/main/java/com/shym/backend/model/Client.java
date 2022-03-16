package com.shym.backend.model;

import com.shym.backend.enumeration.CarCategory;
import com.shym.backend.enumeration.CarModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Client extends User{

    @NotNull(message = "firstName should not be null!")
    private String firstName;

    @NotNull(message = "lastName should not be null!")
    private String lastName;

    @ElementCollection(targetClass = CarCategory.class)
    @Enumerated(EnumType.STRING)
    private List<CarCategory> faveCategories;

    @ElementCollection(targetClass = CarModel.class)
    @Enumerated(EnumType.STRING)
    private List<CarModel> faveModels;

    private String lastLocation;

    public Client(String id, String phone, String email, String password, String firstName, String lastName, List<CarCategory> faveCategories, List<CarModel> faveModels, String lastLocation) {
        super(id, phone, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.faveCategories = faveCategories;
        this.faveModels = faveModels;
        this.lastLocation = lastLocation;
    }

    public Client() {}

    public List<CarModel> getFaveModels() {
        return faveModels;
    }

    public void setFaveModels(List<CarModel> faveModels) {
        this.faveModels = faveModels;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<CarCategory> getFaveCategories() {
        return faveCategories;
    }

    public void setFaveCategories(List<CarCategory> faveCategories) {
        this.faveCategories = faveCategories;
    }
}
