package aoc2020;


import utils.FileReader;
import java.util.HashSet;
import java.util.List;

public class Day05 {
    private HashSet<Long> seatIds = new HashSet<>();

    public static void main(String[] args) {
        Day05 day = new Day05();
        day.solve();
    }

    private void solve() {
        String dataPath = "resources/aoc2020/day5-1.txt";
        FileReader fr = new FileReader(dataPath);
        List<String> data = fr.readFile();

        System.out.println(data.stream()
                .map(this::countId)
                .max(Long::compareTo)
        );

        System.out.println(data.stream()
                .map(this::countId)
                .sorted(Long::compareTo)
                .filter(this::isMySeat)
                .map(seat -> seat - 1)  //isMySeat finds the +1 id next to your seat
                .max(Long::compareTo)
        );
    }

    private boolean isMySeat(Long seatId) {
        seatIds.add(seatId);
        return !seatIds.contains(seatId - 1);
    }

    private long countId(String s) {
        int rowMin = 0;
        int rowMax = 127;
        int colMin = 0;
        int colMax = 7;

        for (int i = 0; i < s.length(); i++) {
            int rowDelta = (rowMax - rowMin + 1) / 2;
            int colDelta = (colMax - colMin + 1) / 2;
            if(s.charAt(i) == 'F'){
                rowMax -= rowDelta;
            } else if( s.charAt(i) == 'B'){
                rowMin += rowDelta;
            } else if( s.charAt(i) == 'L'){
                colMax -= colDelta;
            } else if( s.charAt(i) == 'R'){
                colMin += colDelta;
            } else {
                System.out.println("Err..." + s);
            }
        }
        return (long)rowMax * 8 + colMax;

    }
}
