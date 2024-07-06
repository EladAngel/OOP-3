package Model.tile;
import Model.Board.SemiBoard;
import Model.Utils.Position;
import Model.tile.units.Unit;

public class Wall extends Tile{
    protected static final char WALL_CHAR='#';
    public Wall(Position pos, SemiBoard semi){
        super(WALL_CHAR,pos,semi);
    }
    public void accept(Unit u){
        u.visit(this);
    }

}
