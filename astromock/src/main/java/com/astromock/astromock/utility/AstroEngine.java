package com.astromock.astromock.utility;

import java.time.LocalDate;

public class AstroEngine {

    private static final String[] zodiac = {
            "Capricorn","Aquarius","Pisces","Aries",
            "Taurus","Gemini","Cancer","Leo",
            "Virgo","Libra","Scorpio","Sagittarius"
    };

    private static final String[] nakshatra = {
            "Ashwini","Bharani","Krittika","Rohini",
            "Mrigashira","Ardra","Punarvasu","Pushya"
    };

    public static String zodiac(LocalDate dob) {
        return zodiac[dob.getMonthValue() - 1];
    }

    public static String nakshatra(LocalDate dob) {
        return nakshatra[dob.getDayOfMonth() % nakshatra.length];
    }
}
