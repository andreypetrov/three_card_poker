package com.petrovdevelopment;

import java.util.Arrays;
import java.util.Comparator;

/**
 * contains sorted by rank array of cards
 */
public class Hand {
    private final int playerId;
    private final Card[] cards;
    private HandScore handScore;

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
     * Since a hand's list of cards is immutable and every card is immutable itself, this means hand score will never change.
     * @return
     */
    public HandScore getHandScore() {
        if (handScore == null) handScore = PokerHandScoringStrategy.calculateHandScore(this);
        return handScore;
    }

    public int getPlayerId() {
        return playerId;
    }

    /**
     * calling method size() instead of getSize() to match standard java notation.
     * @return
     */
    public int size() {
        return cards.length;
    }

    public Card getCard(int index) {
        return cards[index];
    }

    /**
     * Needed only for debugging purposes
     * @return
     */
    @Override
    public String toString() {
        String result = String.valueOf(playerId);
        for (Card card : cards) {
            result += " " + card;
        }
        return result;
    }

}
