package com.petrovdevelopment;

/**
 * Methods required to score cards
 *
 */
public class PokerHandScoringStrategy {

    /**
     * Comparison based on standard 3-cards-poker scoring method.
     * This and other underlying implementation methods require cards in hands to be sorted in ascending order.
     * @param hand1
     * @param hand2
     * @return
     */
    public static int compare(Hand hand1, Hand hand2) {
        HandScore score1 = hand1.getHandScore();
        HandScore score2 = hand2.getHandScore();
        if (score1 != score2) return score1.value - score2.value;
        else if (score1 == HandScore.PAIR) return compareHandsWithPairs(hand1, hand2);
        else return compareHighestCards(hand1, hand2);
    }

    public static HandScore calculateHandScore(Hand hand) {
        //we could store locally some of the results of those boolean methods for optimization,
        //but that would reduce readability a bit, and in hands with just a few cards performance is unlikely critical, so optimization would be premature.
        if (isStraight(hand) && isFlush(hand)) return HandScore.STRAIGHT_FLUSH;
        if (isThreeOfAKind(hand)) return HandScore.THREE_OF_A_KIND;
        if (isStraight(hand)) return HandScore.STRAIGHT;
        if (isFlush(hand)) return HandScore.FLUSH;
        if (isPair(hand)) return HandScore.PAIR;
        return HandScore.HIGH_CARD;
    }

    /**
     * Logic should work for 5 cards hand too.
     * @param hand
     * @return
     */
    private static boolean isStraight(Hand hand) {
        int n = hand.cards.length;
        for (int i = 1; i < n - 1; i++) {
            if (hand.cards[i].getRank() - hand.cards[i - 1].getRank() != 1) return false;
        }

        if (hand.cards[n - 1].getRank() - hand.cards[n - 2].getRank() == 1) return true;
        if (hand.cards[n - 1].getRank() == Card.PRIMARY_ACE_RANK
                && hand.cards[0].getRank() - Card.SECONDARY_ACE_RANK == 1) return true;

        return false;
    }

    private static boolean isFlush(Hand hand) {
        Card.Suite suite = hand.cards[0].getSuite();
        for (int i = 1; i < hand.cards.length; i++) {
            if (hand.cards[i].getSuite() != suite) return false;
        }
        return true;
    }

    private static boolean isThreeOfAKind(Hand hand) {
        int rank = hand.cards[0].getRank();
        for (int i = 1; i < hand.cards.length; i++) {
            if (hand.cards[i].getRank() != rank) return false;
        }
        return true;
    }

    private static boolean isPair(Hand hand) {
        return pairRank(hand) != -1;
    }

    private static int compareHandsWithPairs(Hand hand1, Hand hand2) {
        int pairRank1 = pairRank(hand1);
        int pairRank2 = pairRank(hand2);
        if (pairRank1 == pairRank2) return compareHighestCards(hand1, hand2);
        return pairRank1 - pairRank2;
    }

    /**
     * get the rank of the first pair in a hand of cards. In three cards poker there is only one
     *
     * @param hand
     * @return rank of a pair or -1 if there is no pair
     */
    private static int pairRank(Hand hand) {
        for (int i = 0; i < hand.cards.length - 1; i++) {
            for (int j = i + 1; j < hand.cards.length - 1; j++) {
                if (hand.cards[i].getRank() == hand.cards[j].getRank()) return hand.cards[i].getRank();
            }
        }
        return -1;
    }

    /**
     * In case of a category tie, the hand with highest card will win.
     * Logic should work for 5 cards hands too.
     * @param hand1
     * @param hand2
     * @return
     */
    private static int compareHighestCards(Hand hand1, Hand hand2) {
        for (int i = hand1.cards.length - 1; i >= 0; i--) {
            if (hand1.cards[i].compareTo(hand2.cards[i]) != 0) {
                return hand1.cards[i].compareTo(hand2.cards[i]);
            }
        }
        return 0;
    }

}
