package com.petrovdevelopment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandScoringStrategyTest {

    @Test
    void testCalculatePairRank() {
        Hand hand = new Hand(1, new String[]{"2h", "7h", "7d"});
        assertEquals(7, PokerHandScoringStrategy.calculatePairRank(hand));
    }

    @Test
    void testCalculateHandScoreStraightFlush() {
        Hand hand = new Hand(1, new String[]{"2h", "3h", "4h"});
        assertEquals(HandScore.STRAIGHT_FLUSH, PokerHandScoringStrategy.calculateHandScore(hand));
    }

    @Test
    void testThreeOfAKind() {
        Hand hand = new Hand(1, new String[]{"2h", "2d", "2c"});
        assertEquals(HandScore.THREE_OF_A_KIND, PokerHandScoringStrategy.calculateHandScore(hand));
    }

    @Test
    void testStraight() {
        Hand hand = new Hand(1, new String[]{"3d", "2h", "4h"});
        assertEquals(HandScore.STRAIGHT, PokerHandScoringStrategy.calculateHandScore(hand));
    }

    @Test
    void testFlush() {
        Hand hand = new Hand(1, new String[]{"Qh", "7h", "Ah"});
        assertEquals(HandScore.FLUSH, PokerHandScoringStrategy.calculateHandScore(hand));
    }

    @Test
    void testPair() {
        Hand hand = new Hand(1, new String[]{"Td", "Ah", "Th"});
        assertEquals(HandScore.PAIR, PokerHandScoringStrategy.calculateHandScore(hand));
    }

    @Test
    void testHighCard() {
        Hand hand = new Hand(1, new String[]{"Td", "Ah", "2s"});
        assertEquals(HandScore.HIGH_CARD, PokerHandScoringStrategy.calculateHandScore(hand));
    }


}