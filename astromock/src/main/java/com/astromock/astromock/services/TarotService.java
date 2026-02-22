package com.astromock.astromock.services;

import com.astromock.astromock.model.TarotCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TarotService {

    private final List<TarotCard> deck = List.of(
            new TarotCard("The Fool", "New beginnings, leap of faith."),
            new TarotCard("The Magician", "Manifestation, power."),
            new TarotCard("The High Priestess", "Intuition, wisdom."),
            new TarotCard("The Empress", "Abundance, creativity."),
            new TarotCard("The Emperor", "Authority, leadership."),
            new TarotCard("The Lovers", "Union, harmony.")
    );

    public TarotCard getRandomCard() {
        return deck.get(new Random().nextInt(deck.size()));
    }
}