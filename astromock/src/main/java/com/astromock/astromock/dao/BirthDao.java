package com.astromock.astromock.dao;

import com.astromock.astromock.model.BirthRequest;
import com.astromock.astromock.repository.BirthRepository;
import com.astromock.astromock.services.BirthSvc;
import org.springframework.beans.factory.annotation.Autowired;

public class BirthDao implements BirthSvc {
    @Autowired
    private BirthRepository birthRepository;
    @Override
    public String createData(BirthRequest birthRequest) {
        birthRepository.save(birthRequest);
        return "SUCCESS";

    }
}
