package com.petrovdevelopment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandComparatorTest {

    @Test
    void testCompare() {
        PokerHandComparator comparator = new PokerHandComparator();
        Hand hand1 = new Hand(1, new String[]{"4h", "3h", "2h"});
        Hand hand2 = new Hand(2, new String[]{"3h", "4h", "5h"});
        assertEquals(-1, comparator.compare(hand1, hand2));
    }

    @Test
    void testComparePairs() {
        PokerHandComparator comparator = new PokerHandComparator();
        Hand hand1 = new Hand(1, new String[]{"4h", "4d", "2h"});
        Hand hand2 = new Hand(2, new String[]{"5h", "5d", "Ah"});
        assertEquals(-1, comparator.compare(hand1, hand2));
    }
}