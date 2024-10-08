package Model.tile.units.enemies.Monster;

import Model.Utils.Generators.Generator;
import Model.Utils.Generators.randomGenerator;
import Model.Utils.Position;
import Model.tile.units.Unit;
import Model.tile.units.enemies.Enemy;
import Model.Board.SemiBoard;
import View.OutPut.MessageCallBack;

import java.util.Random;

public class Monster extends Enemy {
    protected int visionRange;

    public Monster(int XP, int attack, int defense, int HP, String name, char c, Position pos , int range, Generator generator, SemiBoard semiBoard, MessageCallBack mc) {
        super(XP, attack, defense, HP, name, c, pos,generator, semiBoard, mc);
        visionRange = range;

    }
    public void tick(){
        Position pos = semiBoard.getPlayerPosition(visionRange,position);
        if(pos != null) {
            chooseMovement(pos);
        }
        else{
            randomlyMove();
        }
    }
    public void randomlyMove(){
        if(generator.getRandomSign() ==1){
            Position pos = new Position(position.getX() + generator.getRandomSign(), position.getY());
            chooseMovement(pos);
        }
        else{
            Position pos = new Position(position.getX() , position.getY() + generator.getRandomSign());
            chooseMovement(pos);
        }
    }


    public void moveLeft(){
        interact(semiBoard.getTile(position.getX()-1,position.getY()));
    }
    public void moveRight(){
        interact(semiBoard.getTile(position.getX()+1,position.getY()));
    }
    public void moveUp(){
        interact(semiBoard.getTile(position.getX(),position.getY()-1));
    }
    public void moveDown() {
        interact(semiBoard.getTile(position.getX(), position.getY() + 1));
    }
    public void chooseMovement (Position pos){
        int dx = this.position.getX() - pos.getX();
        int dy = this.position.getY() - pos.getY();
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                moveLeft();
            } else
                moveRight();
        } else {
            if (dy > 0) {
                moveUp();
            } else
                moveDown();
        }
    }
    public String getDescription(){
        return super.getDescription()+"         "+"Vision Range: "+visionRange;
    }



}




