package com.astromock.astromock.model;

import jakarta.persistence.*;

@Entity
@Table(name = "brth_dtls")
public class BirthRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usrId;
    private String name;
    private String dob;
    private String tob;
    private String location;

    // No-arg constructor
    public BirthRequest() {
    }

    // All-arg constructor
    public BirthRequest(Long id, String usrId, String name, String dob, String tob, String location) {
        this.id = id;
        this.usrId = usrId;
        this.name = name;
        this.dob = dob;
        this.tob = tob;
        this.location = location;
    }

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTob() {
        return tob;
    }

    public void setTob(String tob) {
        this.tob = tob;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
