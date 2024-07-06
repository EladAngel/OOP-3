package Controller;

import Model.Utils.Pair;
import Model.Utils.Position;
import Model.tile.Empty;
import Model.tile.Tile;
import Model.tile.Wall;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.enemies.Monster.Monster;
import Model.tile.units.players.Hunter.Hunter;
import Model.tile.units.players.Mage.Mage;
import Model.tile.units.players.Player;
import Model.tile.units.players.Rogue.Rogue;
import Model.tile.units.players.Warrior.Warrior;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TileFactory {

    public Pair<TreeMap<Position,Tile>,List<Enemy>> createBoard(List<String> lines, Player player) {
        int i=0;
        TreeMap<Position,Tile> board = new TreeMap<>();
        List<Enemy> enemies = new ArrayList<>();
        for(String line : lines){
            int j=0;
            for(Character c : line.toCharArray()){
               switch(c){
                   case '.':
                       Tile e= new Empty(new Position(i,j));
                       board.put(e.getPosition(),e);
                       break;
                   case '#':
                       Tile w= new Wall(new Position(i,j));
                       board.put(w.getPosition(),w);
                       break;
                   case '@':
                       player.getPosition().setX(i);
                       player.getPosition().setY(j);
                       board.put(player.getPosition(),player);
                   default:
                       Enemy enemy= createEnemy(c,new Position(i,j));
                       board.put(enemy.getPosition(),enemy);
                       enemies.add(enemy);
               }
               j++;
            }
            i++;
        }
        return null;
    }

    private Enemy createEnemy(Character c, Position p) {
        return switch(c){
            case 's' -> new Monster();
            default -> throw new RuntimeException("The man who passes the sentence should swing the sword");

        };
    }

    public Player createPlayer(int i){
        return switch (i) {
            case 1 -> new Warrior(30, 4, 300, "Jon Snow",new Position(-1,-1) , 0);
            case 2 -> new Warrior(20, 4, 400, "The Hound", new Position(-1,-1), 0);
            case 3 -> new Mage(5, 1, 100, "Melisandre", new Position(-1,-1), 300, 30, 15, 5, 6);
            case 4 -> new Mage(25, 4, 250, "Thoros of Myr", new Position(-1,-1), 150, 20, 20, 3, 4);
            case 5 -> new Rogue(40, 2, 150, "Arya Strak", new Position(-1,-1), 20);
            case 6 -> new Rogue(35, 3, 250, "Bronn", new Position(-1,-1), 50);
            case 7 -> new Hunter(30, 2, 220, "Ygritte", new Position(-1,-1), 6);
            default -> throw new RuntimeException("Chaos isn't a pit. Chaos is a ladder");
        };
    }
}
