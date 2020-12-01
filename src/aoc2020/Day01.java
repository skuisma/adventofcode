package aoc2020;

import utils.FileReader;

import java.util.List;

public class Day01 {

    public static void main(String[] args) {
        Day01 day = new Day01();
        day.solve();
    }

    private void solve() {
        String dataPath = "resources/aoc2020/day1-1.txt";
        FileReader fr = new FileReader(dataPath);
        List<Integer> data = fr.readIntFile();

        int part1 = data.stream()
                .map(value -> 2020 - value)
                .filter(value -> data.contains(value))
                .reduce(1, (integer, integer2) -> integer * integer2);
        System.out.println(part1);

        int part2 = data.stream()
                .map(value -> 2020 - value)
                .flatMap(val1 -> data.stream()
                        .map(val2 -> val1 - val2)
                        .filter(val3 -> data.contains(val3))
                )
                .distinct()
                .reduce(1, (integer, integer2) -> integer * integer2);
        System.out.println(part2);
    }
}
