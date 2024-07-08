package Tests;


import Model.Board.Board;
import Model.Board.SemiBoard;
import Model.Utils.Generators.FixedGenerator;
import Model.Utils.Generators.Generator;
import Model.Utils.Position;
import Model.Utils.PositionComparator;
import Model.tile.Empty;
import Model.tile.Wall;
import Model.tile.units.enemies.Monster.Boss.Boss;
import Model.tile.units.enemies.Monster.Monster;
import Model.tile.units.enemies.Trap.Trap;
import Model.tile.units.players.Hunter.Hunter;
import Model.tile.units.players.Mage.Mage;
import Model.tile.units.players.Player;
import Model.tile.units.players.Rogue.Rogue;
import Model.tile.units.players.Warrior.Warrior;
import View.OutPut.CLI;
import View.OutPut.MessageCallBack;
import View.inputReader.InputReader;
import View.inputReader.TestReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class UnitTest {

    private static Player p;
    private static Hunter h;
    private static Warrior w;
    private static Mage m;
    private static Rogue r;
    private static Empty empty;
    private static Wall wall;
    private static Monster monster;
    private static Trap t;
    private static Boss b;




    @BeforeClass
    public static void setUp() {
        List<Character> list= Arrays.stream(new Character[]{'a','s','d','e','w','q'}).toList();
        InputReader input= new TestReader(list,1);
        Generator gen = new FixedGenerator();
        SemiBoard semi= new Board(new TreeMap<>(new PositionComparator()),6,6,new ArrayList<>(),p);
        MessageCallBack mc= new CLI().getMessageCallBack();
        p=new Warrior(30, 4, 300, "Jon Snow",new Position(-1,-1) , 3,input,gen,semi, mc);
    }

    @Test
    public void testAttackMonster() {

    }


}
