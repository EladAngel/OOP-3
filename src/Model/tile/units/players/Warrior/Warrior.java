package Model.tile.units.players.Warrior;

import Model.Utils.Generators.Generator;
import Model.Utils.Generators.randomGenerator;
import Model.Utils.Position;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

import java.util.List;

public class Warrior extends Player {
    protected int abilityCooldown;
    protected int remainingCooldown;
    public static final int ATTACK_GAIN = 2;
    public static final int DEFENSE_GAIN = 1;
    public static final int HEALTH_GAIN = 5;
    public static final int CAST_DEFENSE_GAIN = 5;
    protected randomGenerator gen;


    public Warrior(int attack, int defense, int HP, String name, Position pos, int cooldown){
        super(attack, defense, HP, name, pos);
        this.abilityCooldown = cooldown;
        remainingCooldown = 0;
        gen = new randomGenerator();
    }
    public void levelUp(){
        super.levelUp();
        remainingCooldown = 0;
        HP.heal(healthGain());
        attack+=attackGain();
        defense+=defenseGain();
    }
    public void castAbility(){
        remainingCooldown = abilityCooldown;
        HP.heal(castDefenseGain());
        List<Enemy> list = semiBoard.enemiesNearby(3,position);
        for(int i=0; i<list.size(); i++){
            int index = gen.generate(list.size());
            Enemy e = list.get(index);
            e.takeDamage((int)(0.1*HP.getCurr()));
            if(!e.alive())
                e.onDeath();
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
}
