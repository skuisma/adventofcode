package aoc2020;

import org.javatuples.Pair;
import utils.FileReader;

import java.util.ArrayList;
import java.util.List;

public class Day03 {

    private Character[][] map;
    private int depth;
    private int width;

    public static void main(String[] args) {
        Day03 day = new Day03();
        day.solve();
    }

    private void solve() {
        String dataPath = "resources/aoc2020/day3-1.txt";
        FileReader fr = new FileReader(dataPath);
        List<String> data = fr.readFile();

        depth = data.size();
        width = data.get(0).length();
        map = new Character[depth][width];

        int rowNum = 0;
        for (String row : data) {
            for (int i = 0; i < row.length(); i++) {
                map[rowNum][i] = row.charAt(i);
            }
            rowNum++;
        }

        //Part1
        System.out.println(calculateSlope(3,1));

        //Part2
        List<Pair<Integer,Integer>> slopes = new ArrayList<>();
        slopes.add(new Pair<Integer, Integer>(1,1));
        slopes.add(new Pair<Integer, Integer>(3,1));
        slopes.add(new Pair<Integer, Integer>(5,1));
        slopes.add(new Pair<Integer, Integer>(7,1));
        slopes.add(new Pair<Integer, Integer>(1,2));

        long part2 = slopes.stream()
                .map(pair -> calculateSlope(pair.getValue0(),pair.getValue1()))
                .reduce((long) 1, (a , b) -> a * b);

        System.out.println(part2);

    }

    private long calculateSlope(int right, int down){
        int x = 0;
        List<Character> path = new ArrayList<>();

        for (int y = 0; y < depth; y+=down) {
            path.add(map[y][x]);
            x = x + right >= width ? x + right - width : x + right;
        }

        return path.stream()
                .filter(ch -> ch == '#')
                .count();
    }
}
