package Controller;

import Model.Utils.Pair;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class TextReader
{

    public static List<String> read(String fileName){
        List<String> lines = null;
        try {
            Path path = Paths.get(fileName);
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Error: An error occurred while reading the file '" + fileName + "'.");
        }
        return lines;
    }

    public static Pair<Integer, Integer> getSize(String name) {
        List<String> lines = read(name);
        return new Pair<>(lines.get(0).length(),lines.size());
    }
}
