package com.petrovdevelopment;

public class Card implements Comparable<Card> {
    private static final int CARD_INPUT_LENGTH = 2;

    public enum Suite {DIAMONDS, HEARTS, CLUBS, SPADES;
        public static Suite fromChar(char c) {
            switch (c) {
                case 'c': return Suite.CLUBS;
                case 'h': return Suite.HEARTS;
                case 'd': return Suite.DIAMONDS;
                case 's': return Suite.SPADES;
                default: throw new IllegalArgumentException("illegal card suite");
            }
        }
    }

    public enum Rank {INVALID, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
        public static Rank fromChar(char c) {
            Rank[] ranks = Rank.values();
            if (c >= '2' && c <= '9') return ranks[c - '0'];
            switch (c) {
                case 'T': return Rank.TEN;
                case 'J': return Rank.JACK;
                case 'Q': return Rank.QUEEN;
                case 'K': return Rank.KING;
                case 'A': return Rank.ACE;
                default: throw new IllegalArgumentException("illegal card rank");
            }
        }
    }

    private final Suite suite;
    private final Rank rank;

    public Card(String card) {
        if (card.length()!= CARD_INPUT_LENGTH) {
            throw new IllegalArgumentException("a card should be represented by exactly two characters. First is rank, second is suite.");
        }
        rank = Rank.fromChar(card.charAt(0));
        suite = Suite.fromChar(card.charAt(1));
    }

    @Override
    public int compareTo(Card other) {
        return getRank().ordinal() - other.getRank().ordinal();
    }

    @Override
    public String toString() {
        return "r:" + rank + " s:" + suite;
    }

    public Suite getSuite() {
        return suite;
    }

    public Rank getRank() {
        return rank;
    }
}