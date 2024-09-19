package Model.Board;

import Model.Utils.Position;
import Model.Utils.PositionComparator;
import Model.tile.Empty;
import Model.tile.Tile;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Board implements SemiBoard{
    private final TreeMap<Position, Tile> tiles;
    private final List<Enemy> enemies;
    private Player player;
    private final int width;
    private final int height;
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final List<Character> Cenemies = Arrays.asList('s','k','q','z','b','g','w','M','G','K','B','Q','D');

    public Board(TreeMap<Position,Tile> tiles, int width, int height, List<Enemy>enemies, Player player){
        this.player = player;
        this.tiles = tiles;
        this.width = width;
        this.enemies=enemies;
        this.height = height;
    }

    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
        tiles.replace(enemy.getPosition(),new Empty(enemy.getPosition(),this));
    }

    public Tile getTile(int x, int y) {
        return tiles.get(new Position(x,y));
    }

    public List<Enemy> enemiesNearby(double radius, Position pos) {
        List<Enemy> list = new ArrayList<>();
        for(Enemy enemy: enemies){
            if(pos.getDistance(enemy.getPosition())<=radius)
                list.add(enemy);
        }
        return list;
    }


    public Position getPlayerPosition(int range, Position pos) {
        if(pos.getDistance(player.getPosition())<=range)
            return player.getPosition();
        return null;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy closestEnemy(int range, Position pos) {
        double min=range;
        Enemy ans=null;
        for(Enemy enemy: enemies){
            if(min>=pos.getDistance(enemy.getPosition())) {
                ans = enemy;
                min=pos.getDistance(enemy.getPosition());
            }

        }
        return ans;
    }


    public void replace(Tile tile1, Tile tile2) {
        Position pos= tile1.getPosition();
        tile1.setPosition(tile2.getPosition());
        tile2.setPosition(pos);
        tiles.replace(tile2.getPosition(),tile2);
        tiles.replace(tile1.getPosition(),tile1);
    }

    public String toString(){
        StringBuilder ans=new StringBuilder();
        for(Position pos: tiles.keySet()){
            Tile tile=tiles.get(pos);
            if(tile.toString().equals("@")){
                ans.append(GREEN);
                ans.append(tile);
                ans.append(RESET);

            }
            else if(Cenemies.contains(tile.toString().charAt(0))){
                ans.append(RED);
                ans.append(tile);
                ans.append(RESET);

            }
            else{
                ans.append(tile);
            }
            if(pos.getX()%width==(width-1))
                ans.append("\n");
        }
        return ans.toString();
    }
    public List<Enemy> enemies(){return enemies;}

    public void addCurrentPlayer(Player player) {
        player.setBoard(this.player.getSemi());
        player.getPosition().setX(this.player.getPosition().getX());
        player.getPosition().setY(this.player.getPosition().getY());
        this.tiles.remove(this.player.getPosition());
        this.tiles.put(player.getPosition(),player);
        this.player=player;
    }
}
