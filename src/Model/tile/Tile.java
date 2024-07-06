package Model.tile;

import Model.Board.SemiBoard;
import Model.Utils.Position;
import Model.tile.units.Unit;
import View.OutPut.MessageCallBack;

public abstract class  Tile {
    protected char tile;
    protected Position position;
    protected MessageCallBack messageCallBack;

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
    public void swapPositions(Position p1, Position p2){
        Position p = new Position(p1.getX(), p1.getX());
        p1.setX(p2.getX());
        p1.setY(p2.getY());
        p2.setX(p.getX());
        p2.setY(p.getY());
    }
}
