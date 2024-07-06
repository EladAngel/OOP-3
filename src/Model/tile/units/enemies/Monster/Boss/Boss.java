package Model.tile.units.enemies.Monster.Boss;

import Model.Utils.Position;
import Model.tile.units.enemies.Monster.Monster;

public class Boss extends Monster {
    protected int abilityFrequency;
    protected int combatTicks;

    public Boss(int XP, int attack, int defense, int HP, String name, char c, Position pos, int visionRange, int abilityFrequency) {
        super(XP,attack,defense,HP,name,c,pos,visionRange);
        this.abilityFrequency = abilityFrequency;
        combatTicks = 0;
    }

    public void tick(){
        Position pos = semiBoard.getPlayerPosition(visionRange,position);
        if(pos != null){
            if(combatTicks == abilityFrequency){
                combatTicks = 0;
                castAbility();
            }
            else {
                combatTicks++;
                chooseMovement(pos);
            }
        }
        else{
            combatTicks = 0;
            randomlyMove();
        }
    }
    public void castAbility(){
        battle(semiBoard.getPlayer());

        //TODO chnage to not random
    }
}
