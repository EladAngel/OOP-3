package Model.tile.units.enemies.Monster.Boss;

import Model.Utils.Position;
import Model.tile.units.enemies.Monster.Monster;
import Model.tile.units.players.Player;

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
        Player p = semiBoard.getPlayer();
        messageCallBack.send(getName()+" shoots "+p.getName()+" for "+ attack+" damage.");
        int Defense = p.defense();
        messageCallBack.send(p.getName()+ " rolled "+Defense+" defense points.");
        p.takeDamage(attack - Defense);
        messageCallBack.send(getName()+" hit "+p.getName()+" for"+Math.max(0,attack - Defense)+" ability damage.");
        if(!p.alive())
            p.onDeath();
    }
    public String getDescription(){
        return super.getDescription()+"         "+"Ability Frequency: "+abilityFrequency;
    }
}
