package Model.tile.units.players.Rogue;

import Model.Utils.Position;

public class Bronn extends Rogue{
    protected static final String NAME = "Bronn";
    protected static final int HP = 250;
    protected static final int ATTACK_POINTS = 35;
    protected static final int DEFENSE_POINTS = 3;
    protected static final int COST = 50;

    public Bronn(Position pos) {
        super(ATTACK_POINTS,DEFENSE_POINTS,HP,NAME,pos,COST);
    }
}
