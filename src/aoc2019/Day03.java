package aoc2019;

import javafx.util.Pair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Day03 {
    private Map<String, Integer> map = new HashMap<>();
    private int x = 0;
    private int y = 0;
    private int steps = 0;
    private int rowNumber = 0;
    private int distance = Integer.MAX_VALUE;
    private int totalSteps = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Day03 day = new Day03();
        day.solve();

    }

    private void solve() {
        String dataPath = "src\\resources\\day3-1.txt";

        FileReader fr = new FileReader(dataPath);
        fr.readFile().forEach(row -> {
            x = 0;
            y = 0;
            steps = 0;
            rowNumber++;
            routeWire(row);
        });

        System.out.println(distance);
        System.out.println(totalSteps);
    }

    private void routeWire(String r) {
        Arrays.stream(r.split(","))
            .map(elem -> {
                int[] direction = getDirection(elem.charAt(0));
                int length = Integer.parseInt(elem.substring(1));
                return new Pair<>(direction, length);
            })
            .forEach(w -> {
                for (int i = 0; i < w.getValue(); i++) {
                    int newX = x + w.getKey()[0];
                    int newY = y + w.getKey()[1];
                    steps++;
                    if (map.containsKey(newX + "-" + newY) && rowNumber == 2){
                        distance = Math.min(distance, Math.abs(newX) + Math.abs(newY));
                        totalSteps = Math.min(totalSteps, (map.get(newX + "-" + newY) + steps));
                    } else if (rowNumber == 1){
                        map.put(newX + "-" + newY, steps);
                    }
                    x = newX;
                    y = newY;
                }

            });
    }

    private int[] getDirection(char c){
        switch (c) {
            case 'U': return new int[]{0, 1};
            case 'D': return new int[]{0, -1};
            case 'R': return new int[]{1, 0};
            case 'L': return new int[]{-1, 0};
        }
        return new int[]{};
    }
}
