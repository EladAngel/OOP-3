package Model.tile;

import Model.Utils.Position;
import Model.tile.units.Unit;

public abstract class  Tile {
    protected char tile;
    protected Position position;
    public Tile(char tile, Position position) {
        this.tile = tile;
        this.position = position;
    }
    public abstract void accept(Unit u);
    public String toString(){
        return ""+tile;
    }
    public Position getPosition() {
        return position;
    }
}
