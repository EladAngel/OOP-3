package Model.tile.units.players;

import Model.Utils.Position;
import Model.tile.units.Unit;
import Model.tile.units.enemies.Enemy;

public abstract class Player extends Unit {
    private static final char Player_Tile='@';
    protected static final int ATTACK_GAIN=4;
    protected static final int DEFENSE_GAIN=1;
    protected static final int LEVEL_REQ=50;
    protected static final int HEALTH_GAIN=10;
    protected  int level;
    protected int XP;
    public Player(int attack, int defense, int HP, String name, Position pos) {
        super(attack,defense,HP,name, Player_Tile,pos);
        level = 0;
        XP = 0;
    }
    public void kill(Enemy e){
        addExperience(e.getXP());
        e.death();
    }
    public void levelUp(){
        XP-=levelReq();
        HP.addMax(healthGain());
        HP.heal();
        attack+=attackGain();
        defense+=defenseGain();
        level++;
    }
    protected int healthGain(){
        return level*HEALTH_GAIN;
    }
    protected int attackGain(){
        return level*ATTACK_GAIN;
    }
    protected int defenseGain(){
        return level*DEFENSE_GAIN;
    }
    protected int levelReq(){
        return level*LEVEL_REQ;
    }
    public void addExperience(int exp){
        XP+=exp;
        while(XP>=levelReq()){
            levelUp();
        }
    }
    public void accept(Unit u){
        u.visit(this);
    }

    public void visit(Player p){}
    public void visit(Enemy e){
        //start combat
    }

}
