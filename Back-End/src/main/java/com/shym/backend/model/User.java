package com.shym.backend.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Inheritance
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",  strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String phone;

    @NotNull(message = "Email should not be null!")
    @NotEmpty(message = "Email must not be an empty string")
    @Email(message = "Not an email")
    private String email;

    @NotNull(message = "Password should not be null!")
    @NotEmpty(message = "Password must not be an empty string")
    private String password;

    public User(String id, String phone, String email, String password) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User() {}

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
