package Model.Board;

import Model.Utils.Position;
import Model.tile.Tile;
import Model.tile.units.enemies.Enemy;

import java.util.List;

public interface SemiBoard {
    public Tile getTile(int x, int y);
    public void removeEnemy(Enemy enemy);
    public List<Enemy> enemiesNearby(int radius, Position pos);
    public Position getPlayerPosition(int range, Position pos);
}
