package com.astromock.astromock.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registration")   // Optional but recommended
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String phnno;

    // No-arg constructor (Required by JPA)
    public Registration() {
    }

    // All-args constructor
    public Registration(Long id, String email, String password, String name, String phnno) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phnno = phnno;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhnno() {
        return phnno;
    }

    public void setPhnno(String phnno) {
        this.phnno = phnno;
    }
}