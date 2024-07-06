package Model.Board;

import Model.Utils.Position;
import Model.Utils.PositionComparator;
import Model.tile.Empty;
import Model.tile.Tile;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Board implements SemiBoard{
    private final TreeMap<Position, Tile> tiles;
    private final List<Enemy> enemies;
    private Player player;
    private final int width;
    private final int height;


    public Board(TreeMap<Position,Tile> tiles, int width, int height, List<Enemy>enemies, Player player){
        this.player = player;
        this.tiles = tiles;
        this.width = width;
        this.enemies=enemies;
        this.height = height;
    }

    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
        tiles.remove(enemy.getPosition());
        tiles.put(enemy.getPosition(),new Empty(enemy.getPosition(),enemy.getSemi()));
    }

    public void insert(Tile tile) {
        tiles.put(tile.getPosition(),tile);
    }

    public Tile getTile(int x, int y) {
        return tiles.get(new Position(x,y));
    }

    public void remove(Tile tile) {
        tiles.remove(tile.getPosition());
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

    public String toString(){
        StringBuilder ans=new StringBuilder();
        for(Position pos: tiles.keySet()){
            Tile tile=tiles.get(pos);
            ans.append(tile);
            if(tile.getPosition().getX()%width==(width-1))
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
