package Model.tile.units.players.Mage;

import Model.Board.SemiBoard;
import Model.Utils.Generators.Generator;
import Model.Utils.Generators.randomGenerator;
import Model.Utils.Position;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;
import View.OutPut.MessageCallBack;
import View.inputReader.InputReader;

import java.util.List;

public class Mage extends Player {
    protected int manaPool;
    protected int currMana;
    protected int manaCost;
    protected int spellPower;
    protected int hitCount;
    protected int abilityRange;
    protected static final int MANA_POOL_GAIN = 25;
    protected static final int SPELL_POWER_GAIN = 25;
    protected randomGenerator gen;

    public Mage(int attack, int defense, int HP, String name, Position pos, int manaPool, int manaCost, int spellPower,
                int hitCount, int abilityRange, InputReader inputReader, Generator generator, SemiBoard semiBoard, MessageCallBack mc) {
        super(attack, defense, HP, name, pos, inputReader,generator, semiBoard, mc);
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
        spellPower += spellPowerGain();
        messageCallBack.send(getName()+" reached level "+level+": "+"+"+healthGain()+" Health, "+ "+"+attackGain()+" Attack, "+"+"+defenseGain()+" Defense, "+"+"+manaPoolGain()+" Maximum Mana, "+"+"+spellPowerGain()+" Spell Power");

    }
    public void castAbility(){
        if(enoughResource(currMana - manaCost)){
            messageCallBack.send(getName()+" cast Blizzard.");
            currMana = currMana - manaCost;
            int hits = 0;
            List<Enemy> list = semiBoard.enemiesNearby(abilityRange,position);
            while(hits < hitCount && list != null){
                int index = gen.generate(list.size());
                Enemy e = list.get(index);
                int Defense = e.defense();
                messageCallBack.send(e.getName()+" rolled "+Defense+" defense points.");
                int damageTaken = list.get(index).takeDamage(spellPower - Defense);
                messageCallBack.send(getName()+" hit "+e.getName()+" for "+ damageTaken +" ability damage.");
                if(!e.alive())
                    e.onDeath();
                hits += 1;
                list = semiBoard.enemiesNearby(abilityRange,position);
            }
        }
        else{
            messageCallBack.send(getName()+" tried to cast Blizzard but failed.");
        }
    }
    public void tick(){
        super.tick();
        currMana = Math.min(manaPool, currMana + level);
    }
    public int manaPoolGain(){
        return MANA_POOL_GAIN * level;
    }
    public int currManaGain(){
        return Math.min(currMana + manaPool/4, manaPool);
    }
    public int spellPowerGain(){
        return SPELL_POWER_GAIN * level;
    }
    public String getDescription(){
        return super.getDescription()+"         "+"Mana: "+currMana+"/"+manaPool+"         "+"Spell Power: "+spellPower;
    }
}
