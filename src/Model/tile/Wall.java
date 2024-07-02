package Model.tile;
import Model.Utils.Position;
import Model.tile.units.Unit;

public class Wall extends Tile{
    protected static final char WALL_CHAR='#';
    public Wall(Position pos){
        super(WALL_CHAR,pos);
    }
    public void accept(Unit u){
        u.visit(this);
    }

}
