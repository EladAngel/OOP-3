package Model.tile.units.players.Rogue;

import Model.Board.SemiBoard;
import Model.Utils.Generators.Generator;
import Model.Utils.Position;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;
import View.OutPut.MessageCallBack;
import View.inputReader.InputReader;

import java.util.List;

public class Rogue extends Player {
    protected int cost;
    protected int currentEnergy;
    protected int CURR_ENERGY_INIT;
    protected static final int ATTACK_GAIN = 3;
    protected static final int CURR_ENERGY_GAIN = 100;
    public Rogue(int attack, int defense, int HP, String name, Position pos, int cost, InputReader inputReader,
                 Generator generator, SemiBoard semiBoard, MessageCallBack mc) {
        super(attack, defense, HP, name, pos, inputReader, generator, semiBoard, mc);
        this.cost = cost;
        currentEnergy = CURR_ENERGY_INIT;
    }
    public void levelUp(){

        messageCallBack.send(getName()+" reached level "+(level+1)+": "+"+"+healthGain()+" Health, "+ "+"+attackGain()+" Attack, "+"+"+defenseGain()+" Defense");
        attack += attackGain();
        currentEnergy = currEnergyGain();
        super.levelUp();


    }
    public void castAbility(){
        if(enoughResource(currentEnergy - cost)) {
            messageCallBack.send(getName()+" cast Fan Of Knives.");
            currentEnergy = currentEnergy - cost;
            List<Enemy> list = semiBoard.enemiesNearby(1.5, position);
            for (Enemy e : list) {
                int Defense = e.defense();
                messageCallBack.send(e.getName()+" rolled "+Defense+" defense points.");
                int DamageTaken = e.takeDamage(attack - Defense);
                messageCallBack.send(getName()+" hit "+e.getName()+" for "+ DamageTaken + " ability damage.");
                if (!e.alive())
                    e.onDeath();
            }
        }
        else{
            messageCallBack.send(getName()+" tried to cast Fan Of Knives but failed.");
        }
    }
    public void tick(){
        super.tick();
        currentEnergy = Math.min(currentEnergy + 10,CURR_ENERGY_GAIN);
    }
    protected int attackGain(){
        return level*ATTACK_GAIN;
    }
    protected int currEnergyGain(){
        return CURR_ENERGY_GAIN;
    }
    public String getDescription(){
        return super.getDescription()+"         "+"Energy: "+currentEnergy+"/"+CURR_ENERGY_GAIN;
    }
}
