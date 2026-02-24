package com.astromock.astromock.model;

public class AstrologerResponse {

    private Long id;
    private String name;
    private String photo; // Base64 String
    private String phone;
    private String experience;
    private String description;

    // No-Args Constructor
    public AstrologerResponse() {
    }

    // All-Args Constructor
    public AstrologerResponse(Long id, String name, String photo,
                              String phone, String experience, String description) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.phone = phone;
        this.experience = experience;
        this.description = description;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPhone() {
        return phone;
    }

    public String getExperience() {
        return experience;
    }

    public String getDescription() {
        return description;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}