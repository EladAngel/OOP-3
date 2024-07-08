package Controller;

import Model.Board.SemiBoard;
import Model.Utils.Generators.Generator;
import Model.Utils.Pair;
import Model.Utils.Position;
import Model.tile.Empty;
import Model.tile.Tile;
import Model.tile.Wall;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.enemies.Monster.Boss.Boss;
import Model.tile.units.enemies.Monster.Monster;
import Model.tile.units.enemies.Trap.Trap;
import Model.tile.units.players.Hunter.Hunter;
import Model.tile.units.players.Mage.Mage;
import Model.tile.units.players.Player;
import Model.tile.units.players.Rogue.Rogue;
import Model.tile.units.players.Warrior.Warrior;
import View.OutPut.MessageCallBack;
import View.inputReader.InputReader;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class  TileFactory {

    public void fillBoard(TreeMap<Position,Tile> board, List<Enemy> enemies,
          List<String> lines, Player player, Generator generator, MessageCallBack mc, InputReader in, SemiBoard semi) {
        int j=0;

        for(String line : lines){
            int i=0;
            for(Character c : line.toCharArray()){
               switch(c){
                   case '.':
                       Tile e= new Empty(new Position(i,j),semi);
                       board.put(e.getPosition(),e);
                       break;
                   case '#':
                       Tile w= new Wall(new Position(i,j),semi);
                       board.put(w.getPosition(),w);
                       break;
                   case '@':
                       player.getPosition().setX(i);
                       player.getPosition().setY(j);
                       player.setBoard(semi);
                       board.put(player.getPosition(),player);
                       break;
                   default:
                       Enemy enemy= createEnemy(c,new Position(i,j),generator,semi,mc);
                       board.put(enemy.getPosition(),enemy);
                       enemies.add(enemy);
                       break;
               }
               i++;
            }
            j++;
        }
    }

    private Enemy createEnemy(Character c, Position p,Generator gen, SemiBoard semi, MessageCallBack mc) {
        return switch(c){
            case 's' -> new Monster(25,8,3,80,"Lannister Solider",'s',p,3,gen, semi,mc);
            case 'k' -> new Monster(50,14,8,200,"Lannister Knight", 'k',p,4,gen, semi,mc);
            case 'q' -> new Monster(100, 20,15,400,"Queen's Gaurd",'q',p,5,gen,semi,mc);
            case 'z' -> new Monster(100, 30,15,600,"Wright",'z',p,3,gen,semi,mc);
            case 'b' -> new Monster(250, 75,30,1000,"Bear Wright",'b',p,4,gen,semi,mc);
            case 'g' -> new Monster(500, 100,40,1500,"Giant Wright",'g',p,5,gen,semi,mc);
            case 'w' -> new Monster(1000, 150,40,2000,"White Walker",'w',p,6,gen,semi,mc);
            case 'M' -> new Boss(500, 60,25,1000,"The Mountain",'M',p,6,5,gen,semi,mc);
            case 'C' -> new Boss(1000, 10,10,100,"Queen Cersei",'C',p,1,8,gen,semi,mc);
            case 'K' -> new Boss(5000, 300,150,5000,"Night's King",'K',p,8,3,gen,semi,mc);
            case 'B' -> new Trap(250, 1,1,1,"Bonus Trap",'B',p,1,3,gen,semi,mc);
            case 'Q' -> new Trap(100, 50,10,250,"Queen's Trap",'Q',p,3,7,gen,semi,mc);
            case 'D' -> new Trap(250, 100,20,500,"Queen's Gaurd",'K',p,1,10,gen,semi,mc);
            default -> throw new RuntimeException("The man who passes the sentence should swing the sword");

        };
    }

    public Player createPlayer(int i, InputReader input, Generator gen, SemiBoard semi, MessageCallBack mc){
        return switch (i) {
            case 1 -> new Warrior(30, 4, 300, "Jon Snow",new Position(-1,-1) , 3,input,gen,semi, mc);
            case 2 -> new Warrior(20, 6, 400, "The Hound", new Position(-1,-1), 5,input,gen,semi , mc);
            case 3 -> new Mage(5, 1, 100, "Melisandre", new Position(-1,-1), 300, 30, 15, 5, 6,input,gen,semi, mc);
            case 4 -> new Mage(25, 4, 250, "Thoros of Myr", new Position(-1,-1), 150, 20, 20, 3, 4,input,gen,semi, mc);
            case 5 -> new Rogue(40, 2, 150, "Arya Strak", new Position(-1,-1), 20,input,gen,semi, mc);
            case 6 -> new Rogue(35, 3, 250, "Bronn", new Position(-1,-1), 50,input,gen,semi, mc);
            case 7 -> new Hunter(30, 2, 220, "Ygritte", new Position(-1,-1), 6,input,gen,semi,mc);
            default -> throw new RuntimeException("Chaos isn't a pit. Chaos is a ladder");
        };
    }
}
