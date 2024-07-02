package Model.tile.units.players.Hunter;

import Model.Utils.Position;
import Model.tile.Tile;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

import java.util.List;

public abstract class Hunter extends Player {
    protected int range;
    protected int arrowCount;
    protected int tickCount;
    protected static int ATTACK_GAIN = 2;
    protected static int DEFENSE_GAIN = 1;
    protected static int ARROW_GAIN = 10;

    public Hunter(int attack, int defense, int HP, String name, Position pos, int range){
        super(attack, defense, HP, name, pos);
        this.arrowCount = level*10;
        this.range = range;
        this.tickCount = 0;
    }
    public void levelUp(){
        super.levelUp();
        arrowCount += arrowGain();
    }
    public void castAbility(){
        if(arrowCount != 0){
            Enemy e = ClosestEnemy(range,position);
            if(e != null) {
                e.takeDamage(attack);
                if (!e.alive())
                    kill(e);
                arrowCount--;
            }
        }
    }
    public void tick(){
        //implement
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


}
