package Model.tile;
import Model.Utils.Position;
import Model.tile.units.Unit;

public class Empty extends Tile {
    protected static final char ch='.';
    public Empty(Position pos) {
        super(ch,pos);
    }
    public void accept(Unit u){
        u.visit(this);
    }
}
