package com.astromock.astromock.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class PoojaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    @ManyToOne
    @JsonBackReference

    @JoinColumn(name = "pooja_id")
    private Pooja pooja;

    // No-args constructor
    public PoojaItem() {
    }

    // All-args constructor
    public PoojaItem(Long id, String itemName, Pooja pooja) {
        this.id = id;
        this.itemName = itemName;
        this.pooja = pooja;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Pooja getPooja() {
        return pooja;
    }

    public void setPooja(Pooja pooja) {
        this.pooja = pooja;
    }
}