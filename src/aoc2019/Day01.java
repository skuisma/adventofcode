package aoc2019;

import java.beans.beancontext.BeanContextChild;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day01 {

    public static void main(String[] args) {
        Day01 day = new Day01();
        day.solve2();
    }

    private void solve() {
        String dataPath = "src\\resources\\day1-1.txt";
        FileReader fr = new FileReader(dataPath);
        List<Integer> data = fr.readIntFile();

        int sum = data
                .stream()
                .map(x -> x/3 - 2)
                .mapToInt(v -> v).sum();

        System.out.println(sum);
    }

    private void solve2() {
        String dataPath = "src\\resources\\day1-1.txt";
        FileReader fr = new FileReader(dataPath);
        List<Integer> data = fr.readIntFile();
        List<Integer> sums = new ArrayList<>(data.size());

        for (int i = 0; i < data.size(); i++) {
            while ( data.get(i) > 6 ) {
                int tmp = countFuel(data.get(i));
                int sum = 0;
                int size = sums.size();

                if (i <= size) {
                    sums.add(tmp);
                } else {
                    sum = sums.get(i);
                    sums.set(i, tmp + sum);
                }
                data.set(i, tmp);
            }
        }

        int totalMass = sums.stream().mapToInt(x -> x).sum();
        System.out.println(totalMass);

    }

    private int countFuel(int mass) {
        return (int) mass / 3 -2;
    }
}

