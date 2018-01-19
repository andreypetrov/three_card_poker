package com.petrovdevelopment;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandScoringStrategyTest {
    @org.junit.jupiter.api.Test
    void testCompare() {
        Hand hand1 = new Hand(1, new String[]{"4h", "3h", "2h"});
        Hand hand2 = new Hand(2, new String[]{"3h", "4h", "5h"});

        assertEquals(-1, PokerHandScoringStrategy.compare(hand1, hand2));
    }

    @org.junit.jupiter.api.Test
    void testCalculateHandScore() {
        Hand hand = new Hand(1, new String[]{"2h", "3h", "4h"});
        assertEquals(HandScore.STRAIGHT_FLUSH, hand.getHandScore());
    }

}