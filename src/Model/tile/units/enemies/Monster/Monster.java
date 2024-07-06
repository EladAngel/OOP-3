package Model.tile.units.enemies.Monster;

import Model.Utils.Generators.Generator;
import Model.Utils.Generators.randomGenerator;
import Model.Utils.Position;
import Model.tile.units.Unit;
import Model.tile.units.enemies.Enemy;

public class Monster extends Enemy {
    protected int visionRange;
    protected randomGenerator gen;

    public Monster(int XP, int attack, int defense, int HP, String name, char c, Position pos ,int range) {
        super(XP, attack, defense, HP, name, c, pos);
        visionRange = range;
        gen = new randomGenerator();
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
        //
        }


    public void moveLeft(){
        interact(semiBoard.getTile(position.getX()-1,position.getY()));
    }
    public void moveRight(){
        interact(semiBoard.getTile(position.getX()+1,position.getY()));
    }
    public void moveUp(){
        interact(semiBoard.getTile(position.getX(),position.getY()+1));
    }
    public void moveDown() {
        interact(semiBoard.getTile(position.getX(), position.getY() - 1));
    }
        public void chooseMovement (Position pos){
            int dx = this.position.getX() - pos.getX();
            int dy = this.position.getY() - pos.getY();
            if (dx > dy) {
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
    }




