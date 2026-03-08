package com.astromock.astromock.services;

import com.astromock.astromock.model.ConsultRequest;
import com.astromock.astromock.model.ConsultResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultService {

    private final String OLLAMA_URL = "http://localhost:11434/api/generate";

    public ConsultResponse consult(ConsultRequest req) {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        String prompt = """
                You are a professional Vedic astrology consultant.

                Category: %s
                Question: %s

                Respond strictly in this format:

                Advisor: (short title)
                Guidance: (200 word explanation)
                Remedy: (practical remedy suggestion)
                """.formatted(
                req.getCategory(),
                req.getQuestion()
        );

        Map<String, Object> body = new HashMap<>();
        body.put("model", "phi3");
        body.put("prompt", prompt);
        body.put("stream", false);

        try {

            String response = restTemplate.postForObject(
                    OLLAMA_URL,
                    body,
                    String.class
            );

            JsonNode jsonNode = mapper.readTree(response);
            String aiText = jsonNode.get("response").asText();

            String advisor = extract(aiText, "Advisor:");
            String guidance = extract(aiText, "Guidance:");
            String remedy = extract(aiText, "Remedy:");

            return new ConsultResponse(advisor, guidance, remedy);

        } catch (Exception e) {
            e.printStackTrace();

            return new ConsultResponse(
                    "Astro AI",
                    "AI service temporarily unavailable.",
                    "Please try again later."
            );
        }
    }

    private String extract(String text, String key) {
        try {
            int start = text.indexOf(key) + key.length();
            int end = text.indexOf("\n", start);
            if (end == -1) end = text.length();
            return text.substring(start, end).trim();
        } catch (Exception e) {
            return "Not available";
        }
    }
}