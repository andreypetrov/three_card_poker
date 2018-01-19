package com.petrovdevelopment;

public enum HandScore {
    HIGH_CARD(100),
    PAIR(200),
    FLUSH(300),
    STRAIGHT(400),
    THREE_OF_A_KIND(500),
    STRAIGHT_FLUSH(600);

    int value;
    HandScore(int value) {
        this.value = value;
    }

}
