package com.astromock.astromock.model;

public class KundliRequest {

    private String name;
    private String dob;
    private String time;
    private String place;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    private String fname;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }
}
