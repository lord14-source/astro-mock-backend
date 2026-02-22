package com.astromock.astromock.repository;

import com.astromock.astromock.model.KundaliRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KundaliRepo
        extends JpaRepository<KundaliRecord, Long> {

    List<KundaliRecord> findByEmail(String email);
}
