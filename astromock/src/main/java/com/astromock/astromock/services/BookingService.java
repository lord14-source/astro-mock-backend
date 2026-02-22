package com.astromock.astromock.services;

import com.astromock.astromock.model.Booking;
import com.astromock.astromock.model.Pooja;
import com.astromock.astromock.repository.BookingRepository;
import com.astromock.astromock.repository.PoojaRepository;

import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final PoojaRepository poojaRepo;

    public BookingService(
            BookingRepository bookingRepo,
            PoojaRepository poojaRepo
    ) {
        this.bookingRepo = bookingRepo;
        this.poojaRepo = poojaRepo;
    }

    public Booking createBooking(Long poojaId, Booking booking) {

        // fetch pooja
        Pooja pooja = poojaRepo.findById(poojaId)
                .orElseThrow(() -> new RuntimeException("Pooja not found"));

        // business logic
        booking.setPooja(pooja);
        booking.setRate(pooja.getPrice());

        // save booking
        return bookingRepo.save(booking);
    }
}