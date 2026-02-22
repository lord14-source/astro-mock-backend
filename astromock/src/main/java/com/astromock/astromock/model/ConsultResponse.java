package com.astromock.astromock.model;

public class ConsultResponse {

    private String advisor;
    private String guidance;
    private String remedy;

    public ConsultResponse(String advisor, String guidance, String remedy) {
        this.advisor = advisor;
        this.guidance = guidance;
        this.remedy = remedy;
    }

    public String getAdvisor() {
        return advisor;
    }

    public String getGuidance() {
        return guidance;
    }

    public String getRemedy() {
        return remedy;
    }
}