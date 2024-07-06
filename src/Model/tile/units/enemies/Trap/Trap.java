package Model.tile.units.enemies.Trap;

import Model.Utils.Position;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

public class Trap extends Enemy {
    protected int visibilityTime;
    protected int invisibilityTime;
    protected int tickCount;
    protected boolean visible;
    protected static final int RANGE = 1;

    public Trap(int XP, int attack, int defense, int HP, String name, char c, Position pos , int visibilityTime,int invisibilityTime) {
        super(XP, attack, defense, HP, name, c, pos);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.visible = true;
        this.tickCount = 0;
    }
    public void tick(){
        visible = tickCount < visibilityTime;
        if(tickCount == (visibilityTime + invisibilityTime)){
            tickCount = 0;
        }
        else{
            tickCount++;
        }
        if(semiBoard.getPlayerPosition(RANGE,position)) {
            Player p = semiBoard.getPlayer();
            p.takeDamage(attack);
            if(!p.alive())
                p.onDeath();
        }
        if(visible)
            visibilityTime++;
        else
            invisibilityTime++;
    }


}
