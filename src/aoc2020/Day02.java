package aoc2020;

import utils.FileReader;

import java.util.List;

public class Day02 {

    public static void main(String[] args) {
        Day02 day = new Day02();
        day.solve();
    }

    private void solve() {
        String dataPath = "resources/aoc2020/day2-1.txt";
        FileReader fr = new FileReader(dataPath);
        List<String> data = fr.readFile();

        long part1 = data.stream()
                .map(this::parseData)
                .filter(Passwd::isValid)
                .count();
        System.out.println(part1);

        long part2 = data.stream()
                .map(this::parseData)
                .filter(Passwd::isValid2)
                .count();
        System.out.println(part2);
    }

    private Passwd parseData(String input){
        String[] data = input.split(" |-|:");
        return new Passwd(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[4]);
    }

    //1-3 a: abcde
    private static class Passwd {
        private int min;
        private int max;
        private String key;
        private String password;

        public Passwd(int min, int max, String key, String password){
            this.min = min;
            this.max = max;
            this.key = key;
            this.password = password;
        }

        public boolean isValid(){
            long count = password.chars().filter(ch -> ch == key.charAt(0)).count();
            return min <= count && count <= max;
        }

        public boolean isValid2(){
            int count = 0;
            count += password.charAt(min-1) == key.charAt(0) ? 1 : 0;
            count += password.charAt(max-1) == key.charAt(0) ? 1 : 0;
            return count == 1;
        }
    }
}
