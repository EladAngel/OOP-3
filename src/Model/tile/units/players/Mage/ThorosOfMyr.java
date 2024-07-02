package Model.tile.units.players.Mage;

import Model.Utils.Position;

public class ThorosOfMyr extends Mage{
    protected static final String NAME = "Thoros of Myr";
    protected static final int HP = 250;
    protected static final int ATTACK_POINTS = 25;
    protected static final int DEFENSE_POINTS = 4;
    protected static final int ABILITY_RANGE = 4;
    protected static final int MANA_POOL = 150;
    protected static final int MANA_COST = 20;
    protected static final int SPELL_POWER = 20;
    protected static final int HIT_COUNT = 3;

    public ThorosOfMyr(Position pos) {
        super(ATTACK_POINTS,DEFENSE_POINTS,HP,NAME,pos,MANA_POOL,MANA_COST,SPELL_POWER,HIT_COUNT,ABILITY_RANGE);
    }
}

