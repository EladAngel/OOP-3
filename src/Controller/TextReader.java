package Controller;

import Model.Utils.Pair;

import java.util.List;

public class TextReader
{

    public static List<String> read(String fileName){
        return null;
        //TODO
    }

    public static Pair<Integer, Integer> getSize(String name) {
        List<String> lines = read(name);
        return new Pair<>(lines.get(0).length(),lines.size());
    }
}
