package com.petrovdevelopment;

/**
 * Methods required to score and compare hands of cards
 *
 */
public class PokerHandScoringStrategy {

    /**
     * calculate the hand score of a hand.
     * Method starts with prefix calculate instead of get to underline the fact that there is more work to be done, than simply fetching a score.
     * In a mobile application such method would be good to execute not on the UI thread.
     *
     * @param hand
     * @return
     */
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
     * get the rank of the first pair in a hand of cards. In three cards poker there is only one
     *
     * @param hand
     * @return rank of a pair or -1 if there is no pair
     */
    public static int calculatePairRank(Hand hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.getCard(i).getRank() == hand.getCard(j).getRank()) return hand.getCard(i).getRank();
            }
        }
        return -1;
    }

    /**
     * Logic should work for 5 cards hand too.
     * @param hand
     * @return
     */
    private static boolean isStraight(Hand hand) {
        int n = hand.size();
        for (int i = 1; i < n - 1; i++) {
            if (hand.getCard(i).getRank() - hand.getCard(i - 1).getRank() != 1) return false;
        }

        if (hand.getCard(n - 1).getRank() - hand.getCard(n - 2).getRank() == 1) return true;
        if (hand.getCard(n - 1).getRank() == Card.PRIMARY_ACE_RANK
                && hand.getCard(0).getRank() - Card.SECONDARY_ACE_RANK == 1) return true;

        return false;
    }

    private static boolean isFlush(Hand hand) {
        Card.Suite suite = hand.getCard(0).getSuite();
        for (int i = 1; i < hand.size(); i++) {
            if (hand.getCard(i).getSuite() != suite) return false;
        }
        return true;
    }

    private static boolean isThreeOfAKind(Hand hand) {
        int rank = hand.getCard(0).getRank();
        for (int i = 1; i < hand.size(); i++) {
            if (hand.getCard(i).getRank() != rank) return false;
        }
        return true;
    }

    private static boolean isPair(Hand hand) {
        return calculatePairRank(hand) != -1;
    }



}
