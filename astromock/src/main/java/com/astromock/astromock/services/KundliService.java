package com.astromock.astromock.services;

import com.astromock.astromock.model.KundliRequest;
import com.astromock.astromock.model.KundliResponse;
import org.springframework.stereotype.Service;

@Service
public class KundliService {

    public KundliResponse generate(KundliRequest request) {

        // Example logic — replace with real astrology engine later
        String zodiac = "Leo";
        String planet = "Strong Sun Influence";
        String nakshatra = "Magha";
        String prediction =
                "You possess natural leadership qualities. " +
                        "Career growth and recognition are likely soon.";

        return new KundliResponse(
                zodiac,
                planet,
                nakshatra,
                prediction
        );
    }
}
