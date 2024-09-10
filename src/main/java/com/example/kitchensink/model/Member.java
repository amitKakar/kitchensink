package com.example.kitchensink.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Member {
    @NotNull
    private String id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Email
    private String email;

    // No-argument constructor
    public Member() {
    }

    // Constructor with parameters
    public Member(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
}