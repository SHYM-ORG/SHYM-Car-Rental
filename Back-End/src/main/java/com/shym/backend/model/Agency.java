package com.shym.backend.model;

import com.shym.backend.enumeration.Role;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Agency extends User{

    @NotNull(message = "name is required!")
    private String name;

    @NotNull(message = "location is required!")
    private String location;

    @NotNull(message = "description")
    private String description;

    public Agency(String id, String phone, String email, String password, Role role, String name, String location, String description) {
        super(id, phone, email, password, role);
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public Agency() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
