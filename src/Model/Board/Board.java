package Model.Board;

import Model.Utils.Position;
import Model.tile.Tile;
import jdk.jshell.spi.ExecutionControl;

import java.util.Dictionary;
import java.util.List;
public class Board {
    private Dictionary<Position, Tile> positionTiles;
    private int width;
    private int height;
    public String toString(){
        return "";
    }
    public Board(){}
    public void addTile(Tile tile){}
    public void removeTile(Tile tile){}


}
