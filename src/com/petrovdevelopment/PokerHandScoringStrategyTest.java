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
    void testCalculateHandScoreStraightFlush() {
        Hand hand = new Hand(1, new String[]{"2h", "3h", "4h"});
        assertEquals(HandScore.STRAIGHT_FLUSH, hand.getHandScore());
    }

    @org.junit.jupiter.api.Test
    void testThreeOfAKind() {
        Hand hand = new Hand(1, new String[]{"2h", "2d", "2c"});
        assertEquals(HandScore.THREE_OF_A_KIND, hand.getHandScore());
    }

    @org.junit.jupiter.api.Test
    void testStraight() {
        Hand hand = new Hand(1, new String[]{"3d", "2h", "4h"});
        assertEquals(HandScore.STRAIGHT, hand.getHandScore());
    }

    @org.junit.jupiter.api.Test
    void testFlush() {
        Hand hand = new Hand(1, new String[]{"Qh", "7h", "Ah"});
        assertEquals(HandScore.FLUSH, hand.getHandScore());
    }

       @org.junit.jupiter.api.Test
    void testPair() {
        Hand hand = new Hand(1, new String[]{"Td", "Ah", "Th"});
        assertEquals(HandScore.PAIR, hand.getHandScore());
    }

    @org.junit.jupiter.api.Test
    void testHighCard() {
        Hand hand = new Hand(1, new String[]{"Td", "Ah", "2s"});
        assertEquals(HandScore.HIGH_CARD, hand.getHandScore());
    }


}