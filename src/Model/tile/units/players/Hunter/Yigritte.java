package Model.tile.units.players.Hunter;

import Model.Utils.Position;

public class Yigritte extends Hunter {
    protected static final String NAME = "Yigritte";
    protected static final int HP = 220;
    protected static final int ATTACK_POINTS = 30;
    protected static final int DEFENSE_POINTS = 2;
    protected static final int RANGE = 6;

    public Yigritte(Position pos) {
        super(ATTACK_POINTS, DEFENSE_POINTS, HP, NAME, pos, RANGE);
    }
}
