package Model.tile;
import Model.Board.SemiBoard;
import Model.Utils.Position;
import Model.tile.units.Unit;

public class Empty extends Tile {
    protected static final char ch='.';
    public Empty(Position pos, SemiBoard semi) {
        super(ch,pos,semi );
    }
    public void accept(Unit u){
        u.visit(this);
    }
}
