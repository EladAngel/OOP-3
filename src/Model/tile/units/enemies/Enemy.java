package Model.tile.units.enemies;

import Model.Utils.Position;
import Model.tile.units.Unit;
import Model.tile.units.players.Player;


public abstract class Enemy extends Unit {
    protected int XP;

    public Enemy(int XP, int attack, int defense, int HP, String name, char c, Position pos) {
        super(attack,defense,HP,name,c,pos);
        this.XP=XP;
    }
    public int getXP() {
        return XP;
    }
    public  void death(){
        //delete enemy
    }
    public void visit(Enemy e){}
    public void visit(Player p){
        p.battle(this);
    }
    public void accept(Unit u){
        u.visit(this);
    }

}
