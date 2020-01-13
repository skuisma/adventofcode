package aoc2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day04 {
    public static void main(String[] args) {
        Day04 day = new Day04();
        day.solve2(134792, 675810);
    }

    private void solve(int startNumber, int endNumber) {
        List<Integer> passwords = new ArrayList<>();
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

    private void solve2(int startNumber, int endNumber) {
        List<Integer> passwords = new ArrayList<>();
        for (int i = startNumber; i <= endNumber; i++) {
            String number = String.valueOf(i);
            int previousNumber = 0;
            Map<Integer,Integer> adjacentPairs = new HashMap<>();
            boolean adjacentPair = true;
            boolean increasingNumbers = true;
            for (int j = 0; j < 6; j++) {
                int currentNumber = (int) number.charAt(j);
                if (previousNumber == currentNumber) {
                    int count = 0;
                    for (int k = j; k >= 0 ; k--) {
                        if(number.charAt(k) == currentNumber) {
                            count++;
                        }
                    }
                    adjacentPairs.put(currentNumber, count);
                }
                if (previousNumber > currentNumber) {
                    increasingNumbers = false;
                    break;
                }
                previousNumber = currentNumber;
            }
            for (Map.Entry<Integer, Integer> entry : adjacentPairs.entrySet()) {
                Integer v = entry.getValue();
                if (v % 2 != 0) {
                    adjacentPair = false;
                }
            }
            //System.out.printf("%d - %b | %b%n", i, adjacentPair, increasingNumbers);
            if (adjacentPair && increasingNumbers) {
                passwords.add(i);
            }
        }
        System.out.println(passwords.size());
    }
}
