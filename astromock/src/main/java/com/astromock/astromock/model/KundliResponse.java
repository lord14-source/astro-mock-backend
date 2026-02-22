package com.astromock.astromock.model;

public class KundliResponse {

    private String zodiac;
    private String planet;
    private String nakshatra;
    private String prediction;

    // Default constructor (important for frameworks like Spring/Jackson)
    public KundliResponse() {}

    // Full constructor
    public KundliResponse(String zodiac, String planet,
                          String nakshatra, String prediction) {
        this.zodiac = zodiac;
        this.planet = planet;
        this.nakshatra = nakshatra;
        this.prediction = prediction;
    }

    // Getters
    public String getZodiac() {
        return zodiac;
    }

    public String getPlanet() {
        return planet;
    }

    public String getNakshatra() {
        return nakshatra;
    }

    public String getPrediction() {
        return prediction;
    }

    // Setters
    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setNakshatra(String nakshatra) {
        this.nakshatra = nakshatra;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    @Override
    public String toString() {
        return "KundliResponse{" +
                "zodiac='" + zodiac + '\'' +
                ", planet='" + planet + '\'' +
                ", nakshatra='" + nakshatra + '\'' +
                ", prediction='" + prediction + '\'' +
                '}';
    }
}
