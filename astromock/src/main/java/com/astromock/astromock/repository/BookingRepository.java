package com.astromock.astromock.repository;

import com.astromock.astromock.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {}