package com.astromock.astromock.services;

import com.astromock.astromock.model.Horoscope;
import com.astromock.astromock.repository.HoroscopeRepo;
import com.astromock.astromock.utility.TraitEngine;
import com.astromock.astromock.utility.ZodiacCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AstrologyService {

    @Autowired
    ZodiacCalculator zodiacCalc;
    @Autowired
    TraitEngine traitEngine;
    @Autowired HoroscopeRepo repo;

    public Horoscope generate(String email,String dob){

        String[] d=dob.split("-");
        int year=Integer.parseInt(d[0]);
        int month=Integer.parseInt(d[1]);
        int day=Integer.parseInt(d[2]);

        String zodiac=zodiacCalc.getZodiac(day,month);
        String prediction=traitEngine.getPrediction(zodiac);

        Horoscope h=new Horoscope();
        h.setEmail(email);
        h.setPrediction(zodiac+" → "+prediction);
        h.setCreatedAt(LocalDateTime.now());

        repo.save(h);

        return h;
    }



    public List<Horoscope>
    history(String email){

        return repo.findByEmail(email);
    }
}
