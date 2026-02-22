package com.astromock.astromock.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pooja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;

    @OneToMany(mappedBy = "pooja", cascade = CascadeType.ALL)
    @JsonManagedReference

    private List<PoojaItem> items;

    // No-args constructor
    public Pooja() {
    }

    // All-args constructor
    public Pooja(Long id, String name, String description, double price, List<PoojaItem> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.items = items;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<PoojaItem> getItems() {
        return items;
    }

    public void setItems(List<PoojaItem> items) {
        this.items = items;
    }
}