package com.petrovdevelopment;

import java.util.*;

//TODO If time permits add cheating validation of input to make sure there are no repeating cards, e.g. Tc, Tc or 5d, 5d?
public class Main {
    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 23;

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
        Hand[] hands = createHandsFromInput(in);
        in.close();
        PokerHandComparator comparator = new PokerHandComparator();
        List<Hand> winners = selectWinners(hands, comparator);
        System.out.println(toString(winners));
    }

    private static Hand[] createHandsFromInput(Scanner in) {
        int playersCount = in.nextInt();
        if (!isValid(playersCount)) throw new IllegalArgumentException("Invalid player count");
        Hand[] hands = new Hand[playersCount];
        for (int i = 0; i < playersCount; i++) {
            int playerId = in.nextInt();
            String[] handInput = {in.next(), in.next(), in.next()};
            hands[i] = new Hand(playerId, handInput);
        }
        return hands;
    }

    /**
     * O(n) time complexity - linear swipe during which we keep the highest winners found so far
     * @param hands
     * @return
     */
    private static List<Hand> selectWinners(Hand[] hands, Comparator<Hand> comparator) {
        List<Hand> winners = new ArrayList<>();
        winners.add(hands[0]);
        for (int i = 1; i < hands.length; i++) {
            if (comparator.compare(hands[i], winners.get(0)) == 0) { //tied with current winner, so add to winning list
                winners.add(hands[i]);
            } else if (comparator.compare(hands[i],winners.get(0)) > 0) { //found new winner, clean previous
                winners.clear();
                winners.add(hands[i]);
            }
        }
        return winners;
    }

    private static String toString(List<Hand> winners) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < winners.size()-1; i++) {
            sb.append(winners.get(i).getPlayerId());
            sb.append(' ');
        }
        sb.append(winners.get(winners.size()-1).getPlayerId());
        return sb.toString();
    }

    private static boolean isValid(int playerCount) {
        return playerCount >= MIN_PLAYER_COUNT && playerCount <= MAX_PLAYER_COUNT;
    }
}
