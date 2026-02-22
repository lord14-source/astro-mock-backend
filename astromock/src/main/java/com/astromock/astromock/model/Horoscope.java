package com.astromock.astromock.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Horoscope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String prediction;
    private LocalDateTime createdAt;

    // No-arg constructor (required by JPA)
    public Horoscope() {
    }

    // All-arg constructor
    public Horoscope(Long id, String email, String prediction, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.prediction = prediction;
        this.createdAt = createdAt;
    }

    // Getters & setters

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

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
