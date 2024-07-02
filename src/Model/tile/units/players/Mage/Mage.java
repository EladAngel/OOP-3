package Model.tile.units.players.Mage;

import Model.Utils.Generators.Generator;
import Model.Utils.Generators.randomGenerator;
import Model.Utils.Position;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

import java.util.List;

public abstract class Mage extends Player {
    protected int manaPool;
    protected int currMana;
    protected int manaCost;
    protected int spellPower;
    protected int hitCount;
    protected int abilityRange;
    protected static final int MANA_POOL_GAIN = 25;
    protected static final int SPELL_POWER_GAIN = 25;
    protected Generator gen;

    public Mage(int attack, int defense, int HP, String name, Position pos, int manaPool, int manaCost, int spellPower, int hitCount, int abilityRange) {
        super(attack, defense, HP, name, pos);
        this.manaPool = manaPool;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.abilityRange = abilityRange;
        this.currMana = manaPool/4;
        gen = new randomGenerator();
    }
    public void levelUp(){
        super.levelUp();
        manaPool += manaPoolGain();
        currMana = currManaGain();
        spellPower += SpellPowerGain();

    }
    public void castAbility(){
        currMana = currMana - manaCost;
        int hits = 0;
        List<Enemy> list = EnemiesNearby(abilityRange,position);
        while(hits < hitCount && list != null){
            int index = gen.generate(list.size());
            list.get(index).takeDamage(spellPower);
            hits += 1;
            list = EnemiesNearby(abilityRange,position);
        }
    }
    public void tick(){
        //implement
    }
    public int manaPoolGain(){
        return MANA_POOL_GAIN * level;
    }
    public int currManaGain(){
        return Math.min(currMana + manaPool/4, manaPool);
    }
    public int SpellPowerGain(){
        return SPELL_POWER_GAIN * level;
    }
}
