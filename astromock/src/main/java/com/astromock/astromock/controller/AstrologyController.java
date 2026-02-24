package com.astromock.astromock.controller;

import com.astromock.astromock.email_pdf.KundaliPDF;
import com.astromock.astromock.model.*;
import com.astromock.astromock.repository.AstrologerRepository;
import com.astromock.astromock.services.*;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/astro")
public class AstrologyController {

    private final AstrologyService service;
    private final ChartService chartService;
    private final KundliService kundliService;
    private final BookingService bookingService;
    private final ConsultService consultService;
    private final TarotService tarotService;
    private final PoojaService poojaService;
    private final AstrologerRepository astrologerRepository;
    private final AstrologerListService astrologerListService;

    @Autowired
    public AstrologyController(AstrologyService service,
                               ChartService chartService,
                               KundliService kundliService,
                               BookingService bookingService,
                               ConsultService consultService,
                               TarotService tarotService,
                               PoojaService poojaService,
                               AstrologerRepository astrologerRepository,
                               AstrologerListService astrologerListService) {
        this.service = service;
        this.chartService = chartService;
        this.kundliService = kundliService;
        this.bookingService = bookingService;
        this.consultService = consultService;
        this.tarotService = tarotService;
        this.poojaService = poojaService;
        this.astrologerRepository = astrologerRepository;
        this.astrologerListService = astrologerListService;
    }

    // ✅ Horoscope generate
    @PostMapping("/generate")
    public Map<String, String> generate(Authentication auth, @RequestBody BirthRequest req) {
        Horoscope horoscope = service.generate(auth.getName(), req.getDob());
        return Map.of("prediction", horoscope.getPrediction());
    }

    @GetMapping("/debug")
    public String debug(Authentication auth) {
        System.out.println(auth);
        return "OK";
    }

    // ✅ Horoscope history
    @GetMapping("/history")
    public List<Horoscope> horoscopeHistory(Authentication auth) {
        return service.history(auth.getName());
    }

    // ✅ Chart generation
    @PostMapping("/chart")
    public Chart chart(Authentication auth, @RequestBody Map<String, String> req) {
        return chartService.generateChart(req.get("dob"));
    }

    // ✅ Kundli generate
    @PostMapping("/kundli")
    public KundliResponse kundli(@RequestBody KundliRequest req) {
        return kundliService.generate(req);
    }

    // ✅ Kundli PDF
    @PostMapping("/kundli/pdf")
    public ResponseEntity<byte[]> kundliPdf(@RequestBody KundliRequest req) throws Exception {
        KundliResponse k = kundliService.generate(req);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=kundli.pdf")
                .body(KundaliPDF.generate(k));
    }

    // ✅ Consult endpoint
    @PostMapping("/consult")
    public ResponseEntity<ConsultResponse> consult(@RequestBody ConsultRequest req) {
        return ResponseEntity.ok(consultService.consult(req));
    }

    // ✅ Tarot draw card
    @GetMapping("/draw")
    public TarotCard drawCard() {
        return tarotService.getRandomCard();
    }

    // ✅ Pooja endpoints
    @PostMapping("/getpoojalist")
    public List<Pooja> listPoojas() {
        return poojaService.getAll();
    }

    @PostMapping("/pooja")
    public Pooja createPooja(@RequestBody Pooja pooja) {
        return poojaService.save(pooja);
    }

    @DeleteMapping("/pooja/{id}")
    public void deletePooja(@PathVariable Long id) {
        poojaService.delete(id);
    }

    // ✅ Booking
    @PostMapping("/save/{poojaId}")
    public Booking saveBooking(@PathVariable Long poojaId, @RequestBody Booking booking) {
        return bookingService.createBooking(poojaId, booking);
    }

    // ✅ Astrologer endpoints
    @GetMapping("/astrologerlist")
    public List<AstrologerResponse> getAllAstrologers() {
        return astrologerListService.getAllAstrologers();
    }

    @PostMapping("/addAstrologer")
    public String addAstrologer(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String experience,
            @RequestParam String description,
            @RequestParam MultipartFile photo
    ) throws IOException, java.io.IOException {

        Astrologer astrologer = new Astrologer();
        astrologer.setName(name);
        astrologer.setPhone(phone);
        astrologer.setExperience(experience);
        astrologer.setDescription(description);

        // 🔥 Convert MultipartFile to byte[] safely
        if (photo != null && !photo.isEmpty()) {
            astrologer.setPhoto(photo.getBytes());
        }

        astrologerRepository.save(astrologer);

        return "Saved Successfully";
    }

    @Autowired
    private  NumerologyService numerologyService;



    @PostMapping("/numerology")
    public NumerologyResponse calculate(@RequestBody NumerologyRequest request) {

        int lifePath = numerologyService.calculateLifePath(request.getDob());
        int destiny = numerologyService.calculateDestiny(request.getName());

        return new NumerologyResponse(
                lifePath,
                destiny,
                numerologyService.getMeaning(lifePath),
                numerologyService.getMeaning(destiny)
        );
    }


    @Autowired
    private BlogService blogService;
    // ================== BLOG SECTION ==================

    // ✅ Create Blog
    @PostMapping("/blog")
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }

    // ✅ Get All Blogs
    @GetMapping("/blog")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

   

}