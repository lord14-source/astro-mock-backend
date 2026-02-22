package com.astromock.astromock.services;

import com.astromock.astromock.model.Chart;
import com.astromock.astromock.utility.PlanetCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChartService {

    @Autowired
    PlanetCalculator calculator;

    public Chart generateChart(String dob) {
        return calculator.calculate(dob);
    }
}