package Model.tile.units.players.Warrior;

import Model.Utils.Position;
import Model.tile.units.players.Player;

public class Warrior extends Player {
    protected int abilityCooldown;
    protected int remainingCooldown;

    public Warrior(int attack, int defense, int HP, String name, Position pos, int cooldown){
        super(attack, defense, HP, name, pos);
        this.abilityCooldown = cooldown;
        remainingCooldown = 0;
    }
    public void levelUp(){
        super.levelUp();

    }
    public void castAbility(){

    }
}
