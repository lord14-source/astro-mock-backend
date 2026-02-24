package com.astromock.astromock.model;

public class NumerologyResponse {

    private int lifePath;
    private int destiny;
    private String lifePathMeaning;
    private String destinyMeaning;

    public NumerologyResponse() {}

    public NumerologyResponse(int lifePath, int destiny,
                              String lifePathMeaning, String destinyMeaning) {
        this.lifePath = lifePath;
        this.destiny = destiny;
        this.lifePathMeaning = lifePathMeaning;
        this.destinyMeaning = destinyMeaning;
    }

    public int getLifePath() { return lifePath; }
    public int getDestiny() { return destiny; }
    public String getLifePathMeaning() { return lifePathMeaning; }
    public String getDestinyMeaning() { return destinyMeaning; }

    public void setLifePath(int lifePath) { this.lifePath = lifePath; }
    public void setDestiny(int destiny) { this.destiny = destiny; }
    public void setLifePathMeaning(String lifePathMeaning) { this.lifePathMeaning = lifePathMeaning; }
    public void setDestinyMeaning(String destinyMeaning) { this.destinyMeaning = destinyMeaning; }
}