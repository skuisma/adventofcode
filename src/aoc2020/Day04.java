package aoc2020;


import utils.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day04 {

    public static void main(String[] args) {
        Day04 day = new Day04();
        day.solve();
    }

    private void solve() {
        String dataPath = "resources/aoc2020/day4-1.txt";
        FileReader fr = new FileReader(dataPath);
        List<String> data = fr.readFile();

        List<String> parsedData = new ArrayList<>();

        String tempLine = "";
        for (String line : data) {
            if (line.length() == 0) {
                parsedData.add(tempLine);
                tempLine = "";
            } else {
                tempLine = tempLine.concat(" ").concat(line);
            }
        }
        parsedData.add(tempLine);

        /*
        byr (Birth Year)
        iyr (Issue Year)
        eyr (Expiration Year)
        hgt (Height)
        hcl (Hair Color)
        ecl (Eye Color)
        pid (Passport ID)
        cid (Country ID)
         */
        List<String> part1 = parsedData.stream()
                .filter(line -> line.matches(".*byr:.*"))
                .filter(line -> line.matches(".*iyr:.*"))
                .filter(line -> line.matches(".*eyr:.*"))
                .filter(line -> line.matches(".*hgt:.*"))
                .filter(line -> line.matches(".*hcl:.*"))
                .filter(line -> line.matches(".*ecl:.*"))
                .filter(line -> line.matches(".*pid:.*"))
                .collect(Collectors.toList());

        System.out.println(part1.size());

        System.out.println(part1.stream()
                .filter(this::validatePassport)
                .count()
        );
    }

    /*
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    hgt (Height) - a number followed by either cm or in:
        If cm, the number must be at least 150 and at most 193.
        If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.
     */
    private boolean validatePassport(String passport) {
        boolean isValid = true;
        String[] fields = passport.split(" ");
        for (String field : fields){
            String[] values = field.split(":");
            switch (values[0]) {
                case "byr":
                    if ( Integer.parseInt(values[1]) < 1920 || 2002 < Integer.parseInt(values[1])) { isValid = false; }
                    break;
                case "iyr":
                    if ( Integer.parseInt(values[1]) < 2010 || 2020 < Integer.parseInt(values[1])) { isValid = false; }
                    break;
                case "eyr":
                    if ( Integer.parseInt(values[1]) < 2020 || 2030 < Integer.parseInt(values[1])) { isValid = false; }
                    break;
                case "hgt":
                    if (!values[1].matches("\\d+[incm]{2}")) {
                        isValid=false;
                        break;
                    }
                    int len = values[1].length();
                    int height = Integer.parseInt(values[1].substring(0,len-2));
                    String unit = values[1].substring(len-2);
                    if (unit.equals("cm")) {
                        if (height < 150 || 193 < height) { isValid = false; }
                    } else if (unit.equals("in")) {
                        if (height < 59 || 76 < height) { isValid = false; }
                    } else {
                        isValid = false;
                    }
                    break;
                case "hcl":
                    if (!values[1].matches("#[0-9a-f]{6}")) { isValid = false; }
                    break;
                case "ecl":
                    if (!values[1].matches("amb|blu|brn|gry|grn|hzl|oth")) { isValid = false; }
                    break;
                case "pid":
                    if (!values[1].matches("[0-9]{9}")) { isValid = false; }
                    break;
                default:
                    break;
            }
        }
        return isValid;
    }
}
