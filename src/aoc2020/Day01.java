package aoc2020;

import utils.FileReader;

import java.util.List;

public class Day01 {

    public static void main(String[] args) {
        Day01 day = new Day01();
        day.solve1();
    }

    private void solve1() {
        String datapath = "resources/aoc2020/day1-1.txt";
        FileReader fr = new FileReader(datapath);
        List<Integer> data =  fr.readIntFile();
        data.forEach(row -> {
            data.forEach(row2 -> {
                if (row != row2 && row + row2 ==2020){
                    System.out.println("Part 1 : " + row * row2);
                }
            });
        });

        for (Integer integer : data) {
            for (Integer integer2 : data) {
                for (Integer integer3 : data) {
                    if (integer != integer2 && integer != integer3 && integer2 != integer3 && integer + integer2 + integer3 == 2020){
                        System.out.println("Part 2 :" + integer * integer2 * integer3);
                        break;
                    }
                }
            }
        }
    }
}
