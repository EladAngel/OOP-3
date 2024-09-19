package Model.tile.units.players;

import Controller.Game;
import Model.Utils.Generators.FixedGenerator;
import Model.Utils.Generators.Generator;
import View.OutPut.CLI;
import View.inputReader.InputReader;
import View.inputReader.TestReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String path = args[0];
        File directory = new File(path);
        List<String> files = Arrays.asList(directory.list());
        Game game = new Game(files);
        game.run();
    }
    public static char randomChar(){
        Random rand= new Random();
        int x= rand.nextInt(6);
        return switch(x){
            case 0 -> 'q';
            case 1 -> 'w';
            case 2 -> 'e';
            case 3 -> 'a';
            case 4 -> 's';
            case 5 -> 'd';
            default -> 'G';
        };
    }
}
