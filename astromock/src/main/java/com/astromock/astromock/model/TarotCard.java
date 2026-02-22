package com.astromock.astromock.model;

public class TarotCard {

    private String name;
    private String meaning;

    public TarotCard(String name, String meaning) {
        this.name = name;
        this.meaning = meaning;
    }

    public String getName() { return name; }
    public String getMeaning() { return meaning; }
}
