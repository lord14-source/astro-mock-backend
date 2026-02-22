package com.astromock.astromock.utility;

import com.astromock.astromock.model.Chart;
import org.springframework.stereotype.Component;

@Component
public class PlanetCalculator {

    private final String[] signs = {
            "Aries","Taurus","Gemini","Cancer",
            "Leo","Virgo","Libra","Scorpio",
            "Sagittarius","Capricorn","Aquarius","Pisces"
    };

    public Chart calculate(String dob) {

        int seed = dob.hashCode();

        Chart c = new Chart();

        c.setSun(signs[Math.abs(seed) % 12]);
        c.setMoon(signs[Math.abs(seed/3) % 12]);
        c.setMars(signs[Math.abs(seed/7) % 12]);

        return c;
    }
}
