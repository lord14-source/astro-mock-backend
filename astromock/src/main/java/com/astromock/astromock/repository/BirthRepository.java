package com.astromock.astromock.repository;


import com.astromock.astromock.model.BirthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthRepository
        extends JpaRepository<BirthRequest, Long> {
}
