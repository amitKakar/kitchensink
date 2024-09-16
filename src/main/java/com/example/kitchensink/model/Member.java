package com.example.kitchensink.model;

import org.springframework.data.mongodb.core.index.Indexed;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
@Entity
public class Member {
    @Id
    @Indexed(unique = true)
    private String id;

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[^0-9]*$", message = "Name cannot contain numbers")
    private String name;

    @NotNull
    @Email
    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    @Digits(integer = 12, fraction = 0)
    @Size(min = 10, max = 12)
    private String phoneNumber;

    // No-argument constructor
    public Member() {
    }

    // Constructor with parameters
    public Member(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}