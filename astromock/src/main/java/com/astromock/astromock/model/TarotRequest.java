package com.astromock.astromock.model;

import java.util.List;

public class TarotRequest {

    private List<String> cards;

    public TarotRequest() {}

    public TarotRequest(List<String> cards) {
        this.cards = cards;
    }

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }
}