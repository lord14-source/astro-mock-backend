package com.astromock.astromock.services;

import com.astromock.astromock.model.KundaliRecord;
import com.astromock.astromock.model.KundliRequest;
import com.astromock.astromock.model.KundliResponse;
import com.astromock.astromock.repository.KundaliRepo;
import com.astromock.astromock.utility.AstroEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KundaliService {

    @Autowired
    KundaliRepo repo;

    public KundliResponse generate(
            String email,
            KundliRequest req) {

        LocalDate dob = LocalDate.parse(req.getDob());

        String zodiac =
                AstroEngine.zodiac(dob);

        String nakshatra =
                AstroEngine.nakshatra(dob);

        String prediction =
                AIInterpreter.interpret(zodiac);

        KundaliRecord record =
                new KundaliRecord();

        record.setEmail(email);
        record.setZodiac(zodiac);
        record.setNakshatra(nakshatra);
        record.setPrediction(prediction);

        repo.save(record);

        return new KundliResponse(
                zodiac,
                nakshatra,
                "Sun dominant",
                prediction
        );
    }

    public List<KundaliRecord>
    history(String email) {
        return repo.findByEmail(email);
    }
}
