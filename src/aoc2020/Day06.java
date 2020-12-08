package aoc2020;


import utils.FileReader;

import java.util.*;

public class Day06 {
    public static void main(String[] args) {
        Day06 day = new Day06();
        day.solve();
    }

    private void solve() {
        String dataPath = "resources/aoc2020/day6-1.txt";
        FileReader fr = new FileReader(dataPath);
        List<String> data = fr.readFile();

        List<HashMap> part1 = new ArrayList<>();
        HashMap<Character,Integer> group = new HashMap<>();

        for (String line : data) {
            if (line.length() > 0) {
                for (int i = 0; i < line.length(); i++) {
                    if (group.containsKey(line.charAt(i))) {
                        group.put(line.charAt(i), group.get(line.charAt(i)) + 1);
                    } else {
                        group.put(line.charAt(i), 1);
                    }
                }
            } else {
                part1.add(new HashMap(group));
                group.clear();
            }
        }
        part1.add(new HashMap(group));

        System.out.println(part1.stream()
                .map(g -> g.size())
                .reduce(0, Integer::sum)
        );
    }
}
