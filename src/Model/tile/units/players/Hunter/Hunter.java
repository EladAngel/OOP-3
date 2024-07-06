package Model.tile.units.players.Hunter;

import Model.Board.SemiBoard;
import Model.Utils.Generators.Generator;
import Model.Utils.Position;
import Model.tile.Tile;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;
import View.OutPut.MessageCallBack;
import View.inputReader.InputReader;

import java.util.List;

public  class Hunter extends Player {
    protected int range;
    protected int arrowCount;
    protected int tickCount;
    protected static int ATTACK_GAIN = 2;
    protected static int DEFENSE_GAIN = 1;
    protected static int ARROW_GAIN = 10;

    public Hunter(int attack, int defense, int HP, String name, Position pos, int range,
           InputReader inputReader, Generator generator, SemiBoard semiBoard, MessageCallBack mc){
        super(attack, defense, HP, name, pos, inputReader, generator, semiBoard, mc);
        this.arrowCount = level*10;
        this.range = range;
        this.tickCount = 0;
    }
    public void levelUp(){
        super.levelUp();
        arrowCount += arrowGain();
        messageCallBack.send(getName()+" reached level "+level+": "+"+"+healthGain()+" Health, "+ "+"+attackGain()+" Attack, "+"+"+defenseGain()+" Defense");
    }
    public void castAbility(){
        if(enoughResource(arrowCount)){
            Enemy e = semiBoard.closestEnemy(range,position);
            if(e != null) {
                messageCallBack.send(getName()+" fired an arrow at "+e.getName()+".");
                int Defense = e.defense();
                messageCallBack.send(e.getName()+" rolled "+Defense+" defense points.");
                int damageTaken = e.takeDamage(attack - Defense);
                messageCallBack.send(getName()+" hit "+e.getName()+" for "+ damageTaken+ " ability damage.");
                if (!e.alive())
                    e.onDeath();
            }
            else{
                messageCallBack.send(getName()+" tried to fire an arrow but there were no enemies in range.");
            }
        }
        else{
            messageCallBack.send(getName()+" tried to fire an arrow but failed.");
        }
        arrowCount--;
    }
    public void tick(){
        super.tick();
        if(tickCount == 10){
            arrowCount = arrowCount + level;
            tickCount = 0;
        }
        else
            tickCount++;
    }
    public int arrowGain(){
        return ARROW_GAIN * level;
    }
    public int attackGain(){
        return level*ATTACK_GAIN;
    }
    public int defenseGain(){
        return level*DEFENSE_GAIN;
    }
    public String getDescription(){
        return super.getDescription() +"         "+"Arrows: "+arrowCount+"         "+"Range: "+range;
    }


}
