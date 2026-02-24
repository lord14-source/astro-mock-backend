package com.astromock.astromock.repository;
import com.astromock.astromock.model.Astrologer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AstrologerRepository extends JpaRepository<Astrologer, Long> {
}