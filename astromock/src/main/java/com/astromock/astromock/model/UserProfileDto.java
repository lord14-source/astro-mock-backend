package com.astromock.astromock.model;



public class UserProfileDto {

    private String name;
    private String email;
    private String phnno;

    public UserProfileDto(Users user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phnno = user.getPhnno();
    }

    public UserProfileDto(String name, String email, String phnno) {
        this.name = name;
        this.email = email;
        this.phnno = phnno;
    }

    // getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhnno() { return phnno; }
}