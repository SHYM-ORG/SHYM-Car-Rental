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

    @NotNull(message = "description is required")
    private String description;

    @NotNull(message = "description is required")
    private String phone;

    public Agency(String id, String email, String password, Role role, String name, String location, String description, String phone1) {
        super(id, email, password, role);
        this.name = name;
        this.location = location;
        this.description = description;
        this.phone = phone1;
    }

    public Agency() {}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
