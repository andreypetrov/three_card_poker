package com.petrovdevelopment;

import java.util.Comparator;

public class PokerHandComparator implements Comparator<Hand>{

    /**
     * Comparison based on standard 3-cards-poker scoring method.
     * This and other underlying implementation methods require cards in hands to be sorted in ascending order.
     * @param hand1
     * @param hand2
     * @return
     */
    @Override
    public int compare(Hand hand1, Hand hand2) {
        HandScore score1 = hand1.getHandScore();
        HandScore score2 = hand2.getHandScore();
        if (score1 != score2) return score1.value - score2.value;
        else if (score1 == HandScore.PAIR) return compareHandsWithPairs(hand1, hand2);
        else return compareHighestCards(hand1, hand2);
    }

    private static int compareHandsWithPairs(Hand hand1, Hand hand2) {
        int pairRank1 = PokerHandScoringStrategy.calculatePairRank(hand1);
        int pairRank2 = PokerHandScoringStrategy.calculatePairRank(hand2);
        if (pairRank1 == pairRank2) return compareHighestCards(hand1, hand2);
        return pairRank1 - pairRank2;
    }

    /**
     * In case of a category tie, the hand with highest card will win.
     * Logic should work for 5 cards hands too.
     * @param hand1
     * @param hand2
     * @return
     */
    private static int compareHighestCards(Hand hand1, Hand hand2) {
        for (int i = hand1.size() - 1; i >= 0; i--) {
            if (hand1.getCard(i).compareTo(hand2.getCard(i)) != 0) {
                return hand1.getCard(i).compareTo(hand2.getCard(i));
            }
        }
        return 0;
    }
}