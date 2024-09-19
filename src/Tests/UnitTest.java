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
    private static Board semi;




    @BeforeClass
    public static void setUp() {
        List<Character> list= Arrays.stream(new Character[]{'a','s','d','e','w','q'}).toList();
        InputReader input= new TestReader(list,1);
        Generator gen = new FixedGenerator();
        semi= new Board(new TreeMap<>(new PositionComparator()),6,6,new ArrayList<>(),p);
        MessageCallBack mc= new CLI().getMessageCallBack();
        p=new Warrior(30, 4, 300, "Jon Snow",new Position(-1,-1) , 3,input,gen,semi, mc);
        monster = new Monster(100,50,5,300,"Goblin",'M',  new Position(1,2),4, gen, semi, mc);
        t = new Trap(100,50,5,300,"Goblin",'M',  new Position(1,2),4,4, gen, semi, mc);
    }

@Test
public void testPlayerIntoEmptyTile() {
    Position Epos=new Position(1,1);
    Position Ppos=new Position(1,2);
    empty=new Empty(Epos,semi);
    p.setPosition(Ppos);
    p.interact(empty);
    Assert.assertEquals(Epos, p.getPosition());
    Assert.assertEquals(Ppos,empty.getPosition());
}
@Test
public void testPlayerIntoWall() {
    Position wallPos = new Position(1, 1);
    Position playerPos = new Position(1, 2);

    wall = new Wall(wallPos,semi);
    p.setPosition(playerPos);
    p.interact(wall);

    Assert.assertEquals(playerPos, p.getPosition());
}
@Test
public void testPlayerIntoEnemy() {
    Position enemyPos = new Position(1, 1);
    Position playerPos = new Position(1, 2);

    p.setPosition(playerPos);

    int monsterInitialHealth = monster.health();
    p.interact(monster);

    int expectedHealth = monsterInitialHealth - Math.max(0, p.attack() - monster.defense()+1);
    Assert.assertEquals(expectedHealth, monster.health());

    Assert.assertEquals(playerPos, p.getPosition());
}
@Test
public void testMonsterIntoEmptyTile() {
    Position emptyPos = new Position(2, 2);
    Position monsterPos = new Position(2, 3);

    Empty emptyTile = new Empty(emptyPos, semi);

    monster.interact(emptyTile);

    Assert.assertEquals(emptyPos, monster.getPosition());
    Assert.assertEquals(monsterPos, emptyTile.getPosition());
}
@Test
public void testMonsterIntoPlayer() {
    Position playerPos = new Position(2, 2);
    Position monsterPos = new Position(2, 3);
    monster.setPosition(monsterPos);
    p.setPosition(playerPos);
    monster.setPosition(monsterPos);
    int playerInitialHealth = p.health();
    monster.interact(p);

    int expectedHealth = playerInitialHealth - Math.max(0, monster.attack() - p.defense()-1);
    Assert.assertEquals(expectedHealth, p.health());

    Assert.assertEquals(monsterPos, monster.getPosition());
}
@Test
public void testMonsterIntoEnemy() {
    Position enemyPos = new Position(2, 3);
    Position monsterPos = new Position(2, 2);
    monster.setPosition(monsterPos);
    monster.interact(t);

    Assert.assertEquals(monsterPos, monster.getPosition());
}
}
