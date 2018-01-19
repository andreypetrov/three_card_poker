package com.petrovdevelopment;

import java.util.Arrays;

/**
 * contains sorted array of cards by rank and suite, although suite sorting is irrelevant
 */
public class Hand implements Comparable<Hand>{
    private final int playerId;

    private HandScore handScore;

    Card[] cards;
    public Hand(int playerId, String[] handInput) {
        this.playerId = playerId;
        cards = new Card[handInput.length];
        for (int i = 0; i < handInput.length; i++) {
            cards[i] = new Card(handInput[i]);
        }
        Arrays.sort(cards);
    }

    /**
     * Lazy initialized handscore first time we access it
     * @return
     */
    public HandScore getHandScore() {
        if (handScore == null) handScore = PokerHandScoringStrategy.calculateHandScore(this);
        return handScore;
    }

    @Override
    public String toString() {
        String result = String.valueOf(playerId);
        for (Card card : cards) {
            result += " " + card;
        }
        return result;
    }

    @Override
    public int compareTo(Hand other) {
        return PokerHandScoringStrategy.compare(this, other);
    }

    public int getPlayerId() {
        return playerId;
    }
}
