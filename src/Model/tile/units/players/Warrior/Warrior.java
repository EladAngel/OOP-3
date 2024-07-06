package Model.tile.units.players.Warrior;

import Model.Board.SemiBoard;
import Model.Utils.Generators.Generator;
import Model.Utils.Generators.randomGenerator;
import Model.Utils.Position;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;
import View.OutPut.MessageCallBack;
import View.inputReader.InputReader;

import java.util.List;

public class Warrior extends Player {
    protected int abilityCooldown;
    protected int remainingCooldown;
    public static final int ATTACK_GAIN = 2;
    public static final int DEFENSE_GAIN = 1;
    public static final int HEALTH_GAIN = 5;
    public static final int CAST_DEFENSE_GAIN = 5;


    public Warrior(int attack, int defense, int HP, String name, Position pos,
                   int cooldown, InputReader inputReader, Generator generator, SemiBoard semiBoard, MessageCallBack mc){
        super(attack, defense, HP, name, pos,inputReader,generator,semiBoard, mc);
        this.abilityCooldown = cooldown;
        remainingCooldown = 0;
    }
    public void levelUp(){
        super.levelUp();
        remainingCooldown = 0;
        HP.heal(healthGain());
        attack+=attackGain();
        defense+=defenseGain();
        messageCallBack.send(getName()+" reached level "+level+": "+"+"+healthGain()+" Health, "+ "+"+attackGain()+" Attack, "+"+"+defenseGain()+" Defense");
    }
    public void castAbility(){
        if(remainingCooldown == 0){
            messageCallBack.send(getName()+" used Avenger's shield, healing for "+castDefenseGain()+".");
            remainingCooldown = abilityCooldown;
            HP.heal(castDefenseGain());
            List<Enemy> list = semiBoard.enemiesNearby(3,position);
            for(int i=0; i<list.size(); i++){
                int index = generator.generate(list.size());
                Enemy e = list.get(index);
                int Defense = e.defense();
                messageCallBack.send(e.getName()+" rolled "+Defense+ " defense points.");
                int damageTaken = e.takeDamage((int)((0.1*HP.getCurr())- Defense));
                messageCallBack.send(getName()+" hit "+e.getName()+" for "+damageTaken+ " ability damage.");
                if(!e.alive())
                    e.onDeath();
            }
        }
        else{
            messageCallBack.send(getName()+ " tried to cast Avenger's shield but failed.");
        }

    }
    public void tick(){
        super.tick();
        remainingCooldown--;
    }
    public int attackGain(){
        return level*ATTACK_GAIN;
    }
    public int defenseGain(){
        return level*DEFENSE_GAIN;
    }
    public int healthGain(){
        return level*HEALTH_GAIN;
    }
    public int castDefenseGain(){
        return level*CAST_DEFENSE_GAIN;
    }
    public String getDescription(){
        return super.getDescription()+"         "+"Cooldown: "+remainingCooldown+"/"+abilityCooldown;
    }
}
