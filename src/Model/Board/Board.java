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
        for(Tile tile: tiles.values()){
            if(tile.getPosition().getX()%width==0)
                ans.append("\n");
            ans.append(tile);
        }
        return ans.toString();
    }
    public List<Enemy> enemies(){return enemies;}
}
