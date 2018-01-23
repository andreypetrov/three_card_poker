package com.petrovdevelopment;

import java.util.Arrays;

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
     * calculate the score of a hand.
     * Method name starts with prefix calculate instead of get to underline the fact that there is more work to be done, than simply fetching a score.
     * In a mobile application such method would be good to execute not on the UI thread.
     *
     * @return score of the hand
     */
    public HandScore calculateHandScore() {
        for (int i = HandScore.values().length-1; i >= 0; i--){
            if (HandScore.values()[i].isMatching(this)) return HandScore.values()[i];
        }
        throw new IllegalStateException("hand cannot be scored");
    }

    /**
     * get the rank of the first pair in a hand of cards. In three cards poker there is only one
     *
     * @return rank of a pair or Rank.INVALID if there is no pair.
     */
    public Card.Rank calculatePairRank() {
        for (int i = 0; i < size() - 1; i++) {
            for (int j = i + 1; j < size(); j++) {
                if (getCard(i).getRank() == getCard(j).getRank()) return getCard(i).getRank();
            }
        }
        return Card.Rank.INVALID;
    }

    /**
     * Lazy initialized hand score first time we access it
     * Since a hand's list of cards is immutable and every card is immutable itself, this means hand score will never change.
     * @return poker score of a hand
     */
    public HandScore getScore() {
        if (handScore == null) handScore = calculateHandScore();
        return handScore;
    }

    public int getPlayerId() {
        return playerId;
    }

    /**
     * calling method size() instead of getSize() to match standard java notation.
     * @return number of cards in hand
     */
    public int size() {
        return cards.length;
    }

    public Card getCard(int index) {
        return cards[index];
    }

    /**
     * Needed only for debugging purposes
     * @return playerId and cards
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.valueOf(playerId));
        for (Card card : cards) {
            sb.append(" ").append(card);
        }
        return sb.toString();
    }
}
