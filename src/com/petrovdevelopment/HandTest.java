package com.petrovdevelopment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void testCalculatePairRank() {
        Hand hand = new Hand(1, new String[]{"2h", "7h", "7d"});
        assertEquals(Card.Rank.SEVEN, hand.calculatePairRank());
    }

    @Test
    void testStraightFlush() {
        Hand hand = new Hand(1, new String[]{"2h", "3h", "4h"});
        assertEquals(HandScore.STRAIGHT_FLUSH, hand.getScore());
    }

    @Test
    void testThreeOfAKind() {
        Hand hand = new Hand(1, new String[]{"2h", "2d", "2c"});
        assertEquals(HandScore.THREE_OF_A_KIND, hand.getScore());
    }

    @Test
    void testStraight() {
        Hand hand = new Hand(1, new String[]{"3d", "2h", "4h"});
        assertEquals(HandScore.STRAIGHT, hand.getScore());
    }

    @Test
    void testStraightWithAceAsOne() {
        Hand hand = new Hand(1, new String[]{"Ad", "2h", "3s"});
        assertEquals(HandScore.STRAIGHT, hand.getScore());
    }

    @Test
    void testFlush() {
        Hand hand = new Hand(1, new String[]{"Qh", "7h", "Ah"});
        assertEquals(HandScore.FLUSH, hand.getScore());
    }

    @Test
    void testPair() {
        Hand hand = new Hand(1, new String[]{"Td", "Ah", "Th"});
        assertEquals(HandScore.PAIR, hand.calculateHandScore());
    }

    @Test
    void testHighCard() {
        Hand hand = new Hand(1, new String[]{"Td", "Ah", "2s"});
        assertEquals(HandScore.HIGH_CARD, hand.calculateHandScore());
    }


}