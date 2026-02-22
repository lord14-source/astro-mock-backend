package com.astromock.astromock.services;

import java.util.Map;

public class ChartEngine {

    public static Map<String,String>
    chart() {

        return Map.of(
                "Sun","1st House",
                "Moon","5th House",
                "Mars","10th House"
        );
    }
}

