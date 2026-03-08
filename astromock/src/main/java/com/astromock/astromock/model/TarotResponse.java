package com.astromock.astromock.model;

public class TarotResponse {

    private String interpretation;

    public TarotResponse() {}

    public TarotResponse(String interpretation) {
        this.interpretation = interpretation;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
}