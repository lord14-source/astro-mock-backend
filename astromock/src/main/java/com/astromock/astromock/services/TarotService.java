package com.astromock.astromock.services;

import com.astromock.astromock.model.TarotCard;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TarotService {

    private final String OLLAMA_URL = "http://localhost:11434/api/generate";

    private final List<TarotCard> deck = List.of(
            new TarotCard("The Fool", "New beginnings, leap of faith."),
            new TarotCard("The Magician", "Manifestation, power."),
            new TarotCard("The High Priestess", "Intuition, wisdom."),
            new TarotCard("The Empress", "Abundance, creativity."),
            new TarotCard("The Emperor", "Authority, leadership."),
            new TarotCard("The Lovers", "Union, harmony.")
    );

    public Map<String, Object> drawThreeCards() {

        List<TarotCard> shuffled = new ArrayList<>(deck);
        Collections.shuffle(shuffled);

        List<Map<String, Object>> selected = new ArrayList<>();
        List<String> cardNamesForAI = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            TarotCard card = shuffled.get(i);
            boolean reversed = new Random().nextBoolean();

            Map<String, Object> cardMap = new HashMap<>();
            cardMap.put("name", card.getName());
            cardMap.put("meaning", card.getMeaning());
            cardMap.put("reversed", reversed);

            selected.add(cardMap);

            cardNamesForAI.add(
                    card.getName() + (reversed ? " (Reversed)" : "")
            );
        }

        String interpretation = generateAIInterpretation(cardNamesForAI);

        Map<String, Object> response = new HashMap<>();
        response.put("cards", selected);
        response.put("interpretation", interpretation);

        return response;
    }

    private String generateAIInterpretation(List<String> cards) {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        String cardsText = String.join(", ", cards);

        String prompt = """
                You are a mystical Tarot Grand Master.

                The user has drawn these cards:
                %s

                Explain:
                - Past meaning
                - Present meaning
                - Future meaning
                - Final advice

                Keep response around 250 words.
                Make it spiritual yet practical.
                """.formatted(cardsText);

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
            return jsonNode.get("response").asText().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "The cosmic energies are disturbed. Please try again later.";
        }
    }
}