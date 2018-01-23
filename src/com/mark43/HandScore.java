package com.mark43;

/**
 * Enum containing all possible scores and for every score a boolean whether the hand matches that score or not
 * This allows us theoretically in the future to extend scores.
 * Current implementation depends on ordering of enum values
 */
public enum HandScore implements ScoreMatch {
    HIGH_CARD(100) {
        @Override
        public boolean isMatching(Hand hand) {
            return true;
        }
    },
    PAIR(200) {
        @Override
        public boolean isMatching(Hand hand) {
            return hand.calculatePairRank() != Card.Rank.INVALID;
        }
    },
    FLUSH(300) {
        @Override
        public boolean isMatching(Hand hand) {
            Card.Suite suite = hand.getCard(0).getSuite();
            for (int i = 1; i < hand.size(); i++) {
                if (hand.getCard(i).getSuite() != suite) return false;
            }
            return true;
        }
    },
    STRAIGHT(400) {
        @Override
        public boolean isMatching(Hand hand) {
            int n = hand.size();
            for (int i = 1; i < n - 1; i++) {
                if (hand.getCard(i).getRank().ordinal() - hand.getCard(i - 1).getRank().ordinal() != 1) return false;
            }

            if (hand.getCard(n - 1).getRank().ordinal() - hand.getCard(n - 2).getRank().ordinal() == 1) return true;
            //check if ACE can be used as one instead
            return hand.getCard(n - 1).getRank() == Card.Rank.ACE
                    && hand.getCard(0).getRank().ordinal() - Card.Rank.ONE.ordinal() == 1;

        }
    },
    THREE_OF_A_KIND(500) {
        @Override
        public boolean isMatching(Hand hand) {
            Card.Rank rank = hand.getCard(0).getRank();
            for (int i = 1; i < hand.size(); i++) {
                if (hand.getCard(i).getRank() != rank) return false;
            }
            return true;
        }
    },

    STRAIGHT_FLUSH(600) {
        @Override
        public boolean isMatching(Hand hand) {
            return FLUSH.isMatching(hand) && STRAIGHT.isMatching(hand);
        }
    };

    final int value;
    HandScore(int value) {
        this.value = value;
    }

}
