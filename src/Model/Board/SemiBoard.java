package Model.Board;

import Model.Utils.Position;
import Model.tile.Tile;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

import java.util.List;

public interface SemiBoard {
    public Tile getTile(int x, int y);
    public void removeEnemy(Enemy enemy);
    public List<Enemy> enemiesNearby(double radius, Position pos);
    public Position getPlayerPosition(int range, Position pos);
    public Player getPlayer();
    public Enemy closestEnemy(int range, Position pos);
    public void replace(Tile tile1, Tile tile2);
}