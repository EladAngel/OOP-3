package Model.tile.units.players.Rogue;

import Model.Utils.Position;

public class AryaStark extends Rogue{
    protected static final String NAME = "Arya Stark";
    protected static final int HP = 150;
    protected static final int ATTACK_POINTS = 40;
    protected static final int DEFENSE_POINTS = 2;
    protected static final int COST = 20;

    public AryaStark(Position pos) {
        super(ATTACK_POINTS,DEFENSE_POINTS,HP,NAME,pos,COST);
    }
}
