package com.astromock.astromock.repository;

import com.astromock.astromock.model.Astrologer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AstrologerListRepository extends JpaRepository<Astrologer, Long> {
}