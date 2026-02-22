package com.astromock.astromock.services;
public class AIInterpreter {

    public static String interpret(
            String zodiac) {

        return switch (zodiac) {

            case "Leo" ->
                    "Leadership & fame energy strong.";

            case "Aries" ->
                    "Action-driven destiny.";

            default ->
                    "Balanced karmic path.";
        };
    }
}
