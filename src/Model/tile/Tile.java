package Model.tile;

import Model.Board.SemiBoard;
import Model.Utils.Position;
import Model.tile.units.Unit;
import View.OutPut.MessageCallBack;

public abstract class  Tile {
    protected char tile;
    protected Position position;
    protected SemiBoard semiBoard;


    public Tile(char tile, Position position,SemiBoard semiBoard) {
        this.tile = tile;
        this.position = position;
        this.semiBoard = semiBoard;
    }
    public abstract void accept(Unit u);
    public String toString(){
        return ""+tile;
    }
    public Position getPosition() {
        return position;
    }
    public void swapPositions(Tile toSwap){
        semiBoard.replace(this,toSwap);
    }
    public SemiBoard getSemi() {
        return semiBoard;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}
