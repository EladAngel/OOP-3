package Model.tile.units.enemies.Trap;

import Model.Board.SemiBoard;
import Model.Utils.Generators.Generator;
import Model.Utils.Position;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

public class Trap extends Enemy {

    //TODO add logic player cant attack invisible trap
    protected int visibilityTime;
    protected int invisibilityTime;
    protected int tickCount;
    protected boolean visible;
    protected static final int RANGE = 1;
    protected char visibleTile;

    public Trap(int XP, int attack, int defense, int HP, String name, char c, Position pos , int visibilityTime, int invisibilityTime, Generator generator, SemiBoard semiBoard) {
        super(XP, attack, defense, HP, name, c, pos,generator, semiBoard);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.visible = true;
        this.tickCount = 0;
        this.visibleTile = c;
    }
    public void tick(){
        visible = tickCount < visibilityTime;
        if(tickCount == (visibilityTime + invisibilityTime)){
            tickCount = 0;
        }
        else{
            tickCount++;
        }
        if(semiBoard.getPlayerPosition(RANGE,position) != null) {
            Player p = semiBoard.getPlayer();
            battle(p);
            if(!p.alive())
                p.onDeath();
        }
        if(visible) {
            visibilityTime++;
            tile = visibleTile;
        }
        else {
            invisibilityTime++;
            tile = '.';
        }
    }


}
