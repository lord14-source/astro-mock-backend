package com.astromock.astromock.email_pdf;

import com.astromock.astromock.model.KundliResponse;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;

public class KundaliPDF {

    public static byte[] generate(KundliResponse k) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        // ----- Title -----
        Paragraph title = new Paragraph("Kundali Report")
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER);

        doc.add(title);
        doc.add(new Paragraph("\n"));

        // ----- Content -----
        String zodiac = safe(k.getZodiac());
        String nakshatra = safe(k.getNakshatra());
        String prediction = safe(k.getPrediction());

        doc.add(new Paragraph("Zodiac: " + zodiac));
        doc.add(new Paragraph("Nakshatra: " + nakshatra));
        doc.add(new Paragraph("\nPrediction:\n" + prediction));

        // ----- Footer -----
        doc.add(new Paragraph("\n--- End of Report ---")
                .setTextAlignment(TextAlignment.CENTER));

        doc.close();

        return out.toByteArray();
    }

    // Utility method to avoid null text
    private static String safe(String value) {
        return value == null ? "N/A" : value;
    }
}
