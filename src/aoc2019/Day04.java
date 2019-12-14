package aoc2019;

import java.util.ArrayList;
import java.util.List;

public class Day04 {
    List<Integer> passwords = new ArrayList<>();

    public static void main(String[] args) {
        Day04 day = new Day04();
        day.solve(134792, 675810);
    }

    private void solve(int startNumber, int endNumber) {
        for (int i = startNumber; i <= endNumber; i++) {
            String number = String.valueOf(i);
            int previousNumber = 0;
            boolean adjacentPair = false;
            boolean increasingNumbers = true;
            for (int j = 0; j < 6; j++) {
                int currentNumber = (int) number.charAt(j);
                if (previousNumber == currentNumber) {
                    adjacentPair = true;
                }
                if (previousNumber > currentNumber) {
                    increasingNumbers = false;
                    break;
                }
                previousNumber = currentNumber;
            }
            if (adjacentPair && increasingNumbers) {
                passwords.add(i);
            }
        }
        System.out.println(passwords.size());
    }
}
