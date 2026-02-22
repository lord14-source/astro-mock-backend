package com.astromock.astromock.utility;

import org.springframework.stereotype.Component;

@Component
public class TraitEngine {

    public String getPrediction(String zodiac){

        switch(zodiac){

            case "Aries":
                return "Energy is high. Take initiative.";

            case "Taurus":
                return "Focus on stability today.";

            case "Gemini":
                return "Communication brings success.";

            case "Cancer":
                return "Trust your emotions.";

            case "Leo":
                return "Leadership opportunity appears.";

            case "Virgo":
                return "Detail work pays off.";

            case "Libra":
                return "Seek balance in decisions.";

            case "Scorpio":
                return "Transformation is near.";

            case "Sagittarius":
                return "Adventure calls.";

            case "Capricorn":
                return "Discipline leads to gains.";

            case "Aquarius":
                return "Innovation wins.";

            default:
                return "Creativity flows today.";
        }
    }
}
