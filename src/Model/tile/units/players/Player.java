package Model.tile.units.players;

import Model.Utils.Position;
import Model.tile.units.HeroicUnit;
import Model.tile.units.Unit;
import Model.tile.units.enemies.Enemy;
import View.inputReader.InputReader;

public abstract class Player extends Unit implements HeroicUnit {
    private static char Player_Tile='@';
    protected static final int ATTACK_GAIN=4;
    protected static final int DEFENSE_GAIN=1;
    protected static final int LEVEL_REQ=50;
    protected static final int HEALTH_GAIN=10;
    protected  int level;
    protected int XP;
    protected InputReader inputReader;
    public Player(int attack, int defense, int HP, String name, Position pos) {
        super(attack,defense,HP,name, Player_Tile,pos);
        level = 0;
        XP = 0;
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
    public void tick(){
        char input = inputReader.getInput();
        if(input == 'd'){
            interact(semiBoard.getTile(position.getX()+1,position.getY()));
        }
        if(input == 's'){
            interact(semiBoard.getTile(position.getX(),position.getY()-1));
        }
        if(input == 'a'){
            interact(semiBoard.getTile(position.getX()-1,position.getY()));
        }
        if(input == 'w'){
            interact(semiBoard.getTile(position.getX(),position.getY()+1));
        }
        if(input == 'e'){
            castAbility();
        }

    }

    public void visit(Player p){}
    public void visit(Enemy e){
        battle(e);
        if(!e.alive()){
            e.onDeath();
        }
    }
    public void onDeath(){
        Player_Tile = 'X';
    }
    public boolean enoughResource(int i){
        return i>=0;
    }
    public String getDescription(){
        return super.getDescription()+"         "+"Level: "+level+"         "+"Experience: "+XP;
    }

}
