package com.astromock.astromock.repository;

import com.astromock.astromock.model.Horoscope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoroscopeRepo
        extends JpaRepository<Horoscope,Long> {

    List<Horoscope> findByEmail(String email);
}
