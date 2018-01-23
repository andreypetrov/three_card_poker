package com.mark43;

import java.util.Comparator;


 /**
 *  Comparator based on standard 3-cards-poker scoring. Requires hands to contain sorted cards in ascending order.
 */
public class PokerHandComparator implements Comparator<Hand>{

    /**
     * Requires hands to contain sorted cards in ascending order.
     * @param hand1 first hand
     * @param hand2 second hand
     * @return order depends on sign of returned integer
     */
    @Override
    public int compare(Hand hand1, Hand hand2) {
        HandScore score1 = hand1.getScore();
        HandScore score2 = hand2.getScore();
        if (score1 != score2) return score1.value - score2.value;
        else if (score1 == HandScore.PAIR) return compareHandsWithPairs(hand1, hand2);
        else return compareHighestCards(hand1, hand2);
    }

    private static int compareHandsWithPairs(Hand hand1, Hand hand2) {
        Card.Rank pairRank1 = hand1.calculatePairRank();
        Card.Rank pairRank2 = hand2.calculatePairRank();
        if (pairRank1 == pairRank2) return compareHighestCards(hand1, hand2);
        return pairRank1.ordinal() - pairRank2.ordinal();
    }

    /**
     * In case of a category tie, the hand with highest card will win.
     * Logic should work for 5 cards hands too.
     * @param hand1 first hand
     * @param hand2 second hand
     * @return order depends on sign of returned integer
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
