package aoc2019;

import utils.FileReader;

import java.util.Arrays;

public class Day02 {
    public static void main(String[] args) {
        Day02 day = new Day02();
        day.solve();
        day.solve2();
    }

    private void solve() {
        String dataPath = "src\\resources\\day2-1.txt";
        FileReader fr = new FileReader(dataPath);
        int[] data = Arrays.stream(fr.readFirstRow().split(",")).mapToInt(Integer::parseInt).toArray();
        data[1] = 12;
        data[2] = 2;
        System.out.println(calculate(data));
    }

    private void solve2() {
        String dataPath = "src\\resources\\day2-1.txt";
        FileReader fr = new FileReader(dataPath);
        final int[] data = Arrays.stream(fr.readFirstRow().split(",")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int[] tempData = data.clone();
                tempData[1] = i;
                tempData[2] = j;
                if (calculate(tempData) == 19690720){
                    System.out.printf("Noun: %d%nVerb: %d%nResult: %d", i, j, i*100+j);
                }
            }
        }
    }

    private int calculate(int[] values) {
        for (int i = 0; i < values.length; i+=4) {
            if (values[i] == 1) {
                //sum
                values[values[i+3]] = values[values[i+1]] + values[values[i+2]];
            } else if (values[i] == 2) {
                //multiply
                values[values[i+3]] = values[values[i+1]] * values[values[i+2]];
            }
        }
        return values[0];
    }
}
