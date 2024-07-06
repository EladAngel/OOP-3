import Controller.Game;
import Model.Utils.Generators.FixedGenerator;
import Model.Utils.Generators.Generator;
import View.OutPut.CLI;
import View.inputReader.InputReader;
import View.inputReader.TestReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("level1.txt");
        list.add("level2.txt");
        list.add("level3.txt");
        list.add("level4.txt");
        list.add("level5.txt");
        List<Character> input = new ArrayList<>();
        for(int i = 0; i < 1000; i++){
            randomChar();
        }
        InputReader in = new TestReader(input,1);
        Generator gen= new FixedGenerator();
        CLI cli= new CLI();
        Game game = new Game(list);
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
