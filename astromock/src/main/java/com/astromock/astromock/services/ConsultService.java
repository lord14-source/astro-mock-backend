package com.astromock.astromock.services;

import com.astromock.astromock.model.ConsultRequest;
import com.astromock.astromock.model.ConsultResponse;
import org.springframework.stereotype.Service;

@Service
public class ConsultService {

    public ConsultResponse consult(ConsultRequest req) {

        String guidance;
        String remedy;

        switch (req.getCategory().toLowerCase()) {

            case "career":
                guidance = "Career growth opportunities are approaching.";
                remedy = "Offer water to Sun every Sunday.";
                break;

            case "marriage":
                guidance = "Relationship harmony improves soon.";
                remedy = "Wear a silver ring on Friday.";
                break;

            case "finance":
                guidance = "Financial gains likely with smart planning.";
                remedy = "Donate food on Saturdays.";
                break;

            default:
                guidance = "Positive transformation is coming.";
                remedy = "Practice daily meditation.";
        }

        return new ConsultResponse(
                "Vedic Consultant",
                guidance,
                remedy
        );
    }
}