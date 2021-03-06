package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    private String filePath;

    public FileReader(String path) {
        filePath = path;
    }

    public List<String> readFile(){
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return allLines;
    }

    public List<Integer> readIntFile() {
        List<Integer> allLines = null;
        try {
            allLines = Files
                    .readAllLines(Paths.get(filePath))
                    .stream()
                    .map(v -> Integer.valueOf(v))
                    .collect(Collectors.toList());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return allLines;
    }

    public String readFirstRow() {
        List<String> lines = readFile();
        return lines.get(0);
    }
}
