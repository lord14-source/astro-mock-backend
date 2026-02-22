package com.astromock.astromock.controller;

import com.astromock.astromock.email_pdf.KundaliPDF;
import com.astromock.astromock.model.*;
import com.astromock.astromock.repository.BookingRepository;
import com.astromock.astromock.services.*;

import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/astro")
public class AstrologyController {

    @Autowired
    AstrologyService service;

    @Autowired
    ChartService chartService;

    @Autowired
    KundliService kundliService;

    public AstrologyController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // ✅ Horoscope generate
    @PostMapping("/generate")
    public Map<String, String> generate(
            Authentication auth,
            @RequestBody BirthRequest req) {

        Horoscope horoscope =
                service.generate(auth.getName(), req.getDob());

        return Map.of(
                "prediction",
                horoscope.getPrediction()
        );
    }
    @GetMapping("/debug")
    public String debug(Authentication auth) {

        System.out.println(auth);
        return "OK";
    }


    // ✅ Horoscope history
    @GetMapping("/history")
    public List<Horoscope> horoscopeHistory(
            Authentication auth) {

        return service.history(auth.getName());
    }

    // ✅ Chart generation
    @PostMapping("/chart")
    public Chart chart(
            Authentication auth,
            @RequestBody Map<String, String> req) {

        return chartService.generateChart(
                req.get("dob"));
    }

    // ✅ Kundli generate
    @PostMapping("/kundli")
    public KundliResponse kundli(
            @RequestBody KundliRequest req) {

        return kundliService.generate(req);
    }

    // ✅ Kundli PDF
    @PostMapping("/kundli/pdf")
    public ResponseEntity<byte[]> kundliPdf(

            @RequestBody KundliRequest req) throws Exception {

        KundliResponse k =
                kundliService.generate(req);

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition",
                        "attachment; filename=kundli.pdf")
                .body(KundaliPDF.generate(k));
    }
    @Autowired
    private ConsultService consultService;

    @PostMapping("/consult")
    public ResponseEntity<ConsultResponse> consult(
            @RequestBody ConsultRequest req
    ) {

        return ResponseEntity.ok(
                consultService.consult(req)
        );
    }

    @Autowired
    private  TarotService tarotServiceservice;



    @GetMapping("/draw")
    public TarotCard drawCard() {
        return tarotServiceservice.getRandomCard();
    }

    @Autowired
    private  PoojaService poojaServicervice;


    @PostMapping("/getpoojalist")
    public List<Pooja> list() {
        return poojaServicervice.getAll();
    }

    @PostMapping("/pooja")
    public Pooja create(@RequestBody Pooja pooja) {
        return poojaServicervice.save(pooja);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        poojaServicervice.delete(id);
    }

    private final BookingService bookingService;



    @PostMapping("/save/{poojaId}")
    public Booking saveBooking(
            @PathVariable Long poojaId,
            @RequestBody Booking booking
    ) {
        return bookingService.createBooking(poojaId, booking);
    }

}
