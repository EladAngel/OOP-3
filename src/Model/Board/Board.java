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
    private final Player player;
    private final int width;


    public Board(List<Tile> list, int width, int height, List<Enemy>enemies, Player player){
        this.player = player;
        tiles = new TreeMap<>(new PositionComparator());
        for(Tile tile : list)
            tiles.put(tile.getPosition(), tile);
        this.width = width;
        this.enemies=enemies;
    }

    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
        tiles.put(enemy.getPosition(),new Empty(enemy.getPosition()));
    }


    @Override
    public Tile getTile(int x, int y) {
        return tiles.get(new Position(x,y));
    }


    public List<Enemy> enemiesNearby(int radius, Position pos) {
        List<Enemy> list = new ArrayList<>();
        for(Enemy enemy: enemies){
            if(pos.getDistance(enemy.getPosition())<=radius)
                list.add(enemy);
        }
        return enemies;
    }


    public Position getPlayerPosition(int range, Position pos) {
        if(pos.getDistance(player.getPosition())<=range)
            return player.getPosition();
        return null;
    }

    public String toString(){
        StringBuilder ans=new StringBuilder();
        for(Tile tile: tiles.values()){
            if(tile.getPosition().getX()%width==0)
                ans.append("\n");
            ans.append(tile);
        }
        return ans.toString();
    }
    public Player player(){return player;}
    public List<Enemy> enemies(){return enemies;}
}
