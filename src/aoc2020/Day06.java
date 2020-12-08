package aoc2020;


import utils.FileReader;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day06 {
    public static void main(String[] args) {
        Day06 day = new Day06();
        day.solve();
    }

    private void solve() {
        String dataPath = "resources/aoc2020/day6-1.txt";
        FileReader fr = new FileReader(dataPath);
        List<String> data = fr.readFile();

        List<HashMap> answers = new ArrayList<>();
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
                if (group.containsKey('#')) {
                    group.put('#', group.get('#') + 1);
                } else {
                    group.put('#', 1);
                }
            } else {
                answers.add(new HashMap(group));
                group.clear();
            }
        }
        answers.add(new HashMap(group));

        int part2 = answers.stream()
                .mapToInt(g -> {
                    int count = (int)g.get('#');
                    AtomicInteger yes = new AtomicInteger();
                    g.remove('#');
                    g.forEach((o, o2) -> {
                        if ((int)o2 == count) { yes.addAndGet(1);}

                    });
                    return yes.intValue();
                })
                .sum();

        int part1 = answers.stream()
                .mapToInt(HashMap::size)
                .sum();

        System.out.println(part1 + "\n" + part2);
    }
}
