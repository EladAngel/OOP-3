package Controller;

import Model.Board.Board;
import Model.Utils.Pair;
import Model.Utils.Position;
import Model.tile.Tile;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Hunter.Hunter;
import Model.tile.units.players.Mage.Mage;
import Model.tile.units.players.Player;
import Model.tile.units.players.Rogue.Rogue;
import Model.tile.units.players.Warrior.Warrior;
import View.OutPut.CLI;
import View.OutPut.MessageCallBack;
import View.inputReader.InputReader;
import View.inputReader.ScannerReader;
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

    public Game(List<String> fileNames){
         new Game(fileNames,new ScannerReader());
    }
    public Game(List<String> fileNames, InputReader input){
        this.input=input;
        factory =new TileFactory();
        mc= new CLI().getMessageCallBack();
        player=createPlayer();
        levels=new ArrayList<Level>();
        int i=0;
        for(String name: fileNames){
            List<String> lines=TextReader.read(name);
            Pair<Integer,Integer> boardSize= TextReader.getSize(name);
            Pair<TreeMap<Position, Tile>,List<Enemy>> board=factory.createBoard(lines,player);
            Board b= new Board(
                    board.getFirst(),
                    boardSize.getFirst(),
                    boardSize.getSecond(),
                    board.getSecond(),
                    player );
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
        return factory.createPlayer(i);
    }
}
