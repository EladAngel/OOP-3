package Model.tile.units.players.Rogue;

import Model.Utils.Position;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

import java.util.List;

public class Rogue extends Player {
    protected int cost;
    protected int currentEnergy;
    protected int CURR_ENERGY_INIT;
    protected static final int ATTACK_GAIN = 3;
    protected static final int CURR_ENERGY_GAIN = 100;
    public Rogue(int attack, int defense, int HP, String name, Position pos, int cost) {
        super(attack, defense, HP, name, pos);
        this.cost = cost;
        currentEnergy = CURR_ENERGY_INIT;
    }
    public void levelUp(){
        super.levelUp();
        attack += attackGain();
        currentEnergy = currEnergyGain();
    }
    public void castAbility(){
        if(enoughResource(currentEnergy - cost)) {
            currentEnergy = currentEnergy - cost;
            List<Enemy> list = semiBoard.EnemiesNearby(1, position);
            for (Enemy e : list) {
                e.takeDamage(attack);
                if (!e.alive()) {
                    e.onDeath();
                }
            }
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
}
