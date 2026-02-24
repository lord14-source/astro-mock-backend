package com.astromock.astromock.model;
public class NumerologyRequest {

    private String name;
    private String dob; // format: yyyy-MM-dd

    public NumerologyRequest() {}

    public String getName() { return name; }
    public String getDob() { return dob; }

    public void setName(String name) { this.name = name; }
    public void setDob(String dob) { this.dob = dob; }
}
