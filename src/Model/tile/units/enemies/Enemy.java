package Model.tile.units.enemies;

import Model.Board.SemiBoard;
import Model.Utils.Generators.Generator;
import Model.Utils.Position;
import Model.tile.units.Unit;
import Model.tile.units.players.Player;
import View.OutPut.MessageCallBack;


public abstract class Enemy extends Unit {
    protected int XP;

    public Enemy(int XP, int attack, int defense, int HP, String name, char c, Position pos, Generator generator, SemiBoard semiBoard, MessageCallBack mc) {
        super(attack,defense,HP,name,c,pos,generator,semiBoard ,mc);
        this.XP=XP;
    }
    public int getXP() {
        return XP;
    }
    public void visit(Enemy e){}
    public void visit(Player p){
        battle(p);
        if(!p.alive()){
            p.onDeath();
        }
    }
    public void onDeath(){
        messageCallBack.send(getName()+" died.");
        semiBoard.removeEnemy(this);
        Player p = semiBoard.getPlayer();
        messageCallBack.send(p.getName()+" gained "+getXP()+" experience.");
        p.addExperience(getXP());


    }
    public void tick(){}
    public void accept(Unit u){
        u.visit(this);
    }
    public String getDescription(){
        return super.getDescription()+"         "+"Experience: "+XP;
    }
}
