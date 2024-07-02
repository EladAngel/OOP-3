package Model.tile.units.players.Mage;

import Model.Utils.Position;

public class Melisandre extends Mage{
    protected static final String NAME = "Melisandre";
    protected static final int HP = 100;
    protected static final int ATTACK_POINTS = 5;
    protected static final int DEFENSE_POINTS = 1;
    protected static final int ABILITY_RANGE = 6;
    protected static final int MANA_POOL = 300;
    protected static final int MANA_COST = 30;
    protected static final int SPELL_POWER = 15;
    protected static final int HIT_COUNT = 5;

    public Melisandre(Position pos) {
        super(ATTACK_POINTS,DEFENSE_POINTS,HP,NAME,pos,MANA_POOL,MANA_COST,SPELL_POWER,HIT_COUNT,ABILITY_RANGE);
    }
}
