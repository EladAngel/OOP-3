package Controller;

import Model.Board.Board;
import Model.Utils.Generators.Generator;
import Model.Utils.Pair;
import Model.Utils.Position;
import Model.Utils.PositionComparator;
import Model.tile.Tile;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;
import View.OutPut.CLI;
import View.OutPut.View;
import View.OutPut.MessageCallBack;
import View.inputReader.InputReader;
import View.inputReader.ScannerReader;
import Model.Utils.Generators.randomGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Game {
    private List<Level> levels;
    private Player player;
    private MessageCallBack mc;
    private int currLevel;
    private InputReader input;
    private TileFactory factory;
    private Generator generator;

    public Game(List<String> fileNames){
        new Game(
                fileNames,new ScannerReader(),
                new randomGenerator(), new CLI());
    }

    public Game(List<String> fileNames, InputReader input, Generator generator, View view){
        this.input=input;
        factory =new TileFactory();
        mc= view.getMessageCallBack();
        this.generator=generator;
        player=createPlayer();
        levels=new ArrayList<Level>();
        int i=0;
        for(String name: fileNames){
            List<String> lines=TextReader.read(name);
            Pair<Integer,Integer> boardSize= TextReader.getSize(name);
            List<Enemy> enemies=new ArrayList<>();
            PositionComparator pc=new PositionComparator();
            TreeMap<Position,Tile> map= new TreeMap<>(pc);
            Board b= new Board(
                    map,
                    boardSize.getFirst(),
                    boardSize.getSecond(),
                    enemies,
                    player );
            factory.fillBoard(map, enemies,lines,player, generator,mc,input,b);

            levels.add(new Level(b,i));
            i++;
        }
    }
    public void run(){
        mc.send("When you play the game of thrones, you win or you die. There is no middle ground.");
        for(Level level : levels){
            mc.send("Welcome to chapter "+level.getNum());
            mc.send(level.getBoard().toString());
            level.run();
            if(!player.alive()) {
                mc.send(" Well You died in the game of thrones!");
                break;
            }

        }
        if(player.alive())
            mc.send("Well played you won the game of thrones!");

    }

    public Player createPlayer(){
        mc.send("Select player:\n" +
                "1. Jon Snow             Health: 300/300         Attack: 30              Defense: 4              Level: 1                Experience: 0/50                Cooldown: 0/3\n" +
                "2. The Hound            Health: 400/400         Attack: 20              Defense: 6              Level: 1                Experience: 0/50                Cooldown: 0/5\n" +
                "3. Melisandre           Health: 100/100         Attack: 5               Defense: 1              Level: 1                Experience: 0/50                Mana: 75/300            Spell Power: 15\n" +
                "4. Thoros of Myr                Health: 250/250         Attack: 25              Defense: 4              Level: 1                Experience: 0/50                Mana: 37/150            Spell Power: 20\n" +
                "5. Arya Stark           Health: 150/150         Attack: 40              Defense: 2              Level: 1                Experience: 0/50                Energy: 100/100\n" +
                "6. Bronn                Health: 250/250         Attack: 35              Defense: 3              Level: 1                Experience: 0/50                Energy: 100/100\n" +
                "7. Ygritte              Health: 220/220         Attack: 30              Defense: 2              Level: 1                Experience: 0/50                Arrows: 10              Range: 6");
        int i= input.getNum();
        return factory.createPlayer(i,input,generator,null,mc);
    }
}
