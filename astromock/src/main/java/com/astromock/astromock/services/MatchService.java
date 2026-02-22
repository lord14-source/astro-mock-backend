package com.astromock.astromock.services;

import org.springframework.stereotype.Service;

@Service
public class MatchService {

    public String match(
            String z1,
            String z2) {

        if(z1.equals(z2))
            return "High compatibility";

        return "Moderate compatibility";
    }
}
