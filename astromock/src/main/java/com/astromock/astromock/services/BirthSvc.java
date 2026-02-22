package com.astromock.astromock.services;

import com.astromock.astromock.model.BirthRequest;

public interface BirthSvc {

    String createData(BirthRequest birthRequest);
}
