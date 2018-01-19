package com.petrovdevelopment;

public class Card implements Comparable<Card> {
    public enum Suite {DIAMONDS, HEARTS, CLUBS, SPADES }

    public static final int PRIMARY_ACE_RANK = 14;
    public static final int SECONDARY_ACE_RANK = 1;
    private static final int CARD_INPUT_LENGTH = 2;


    private final Suite suite;
    private final int rank;

    public Card(String card) {
        if (card.length()!= CARD_INPUT_LENGTH) {
            throw new IllegalArgumentException("a card should be represented by exactly two characters. First is rank, second is suite.");
        }
        rank = toRank(card.charAt(0));
        suite = toSuite(card.charAt(1));
    }

    private Suite toSuite(char c) {
        switch (c) {
            case 'c': return Suite.CLUBS;
            case 'h': return Suite.HEARTS;
            case 'd': return Suite.DIAMONDS;
            case 's': return Suite.SPADES;
            default: throw new IllegalArgumentException("illegal card suite");
        }
    }

    private int toRank(char c) {
        if (c >= '2' && c <= '9') return c - '0';
        switch (c) {
            case 'T': return 10;
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            case 'A': return PRIMARY_ACE_RANK;
            default: throw new IllegalArgumentException("illegal card rank");
        }
    }

    @Override
    public int compareTo(Card other) {
        return getRank() - other.getRank();
    }

    @Override
    public String toString() {
        return "r:" + rank + " s:" + suite;
    }


    public Suite getSuite() {
        return suite;
    }

    public int getRank() {
        return rank;
    }
}