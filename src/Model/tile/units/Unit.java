package Model.tile.units;
import Model.tile.Tile;
import Model.Utils.Health;
import Model.Utils.Position;
import Model.Utils.Generators.Generator;
import Model.tile.Wall;
import Model.tile.Empty;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

public abstract class Unit extends Tile {
    protected int attack;
    protected int defense;
    protected Health HP;
    protected String name;
    protected Generator generator;
    protected SemiBoard semiBoard;

    public Unit(int attack, int defense, int HP, String name, char ch, Position pos) {
        super(ch,pos);
        this.attack=attack;
        this.defense=defense;
        this.HP=new Health(HP);
        this.name=name;
    }
    public int attack(){
        return generator.generate(attack);
    }
    public int defense(){
        return generator.generate(defense);
    }
    public void battle(Unit enemy){
        int attack=this.attack();
        int defense=this.defense();
        int damageTaken = enemy.HP.takeDamage(attack-defense);
    }
    public void takeDamage(int damage){
        HP.takeDamage(damage);
    }
    public boolean alive(){return HP.isAlive();}
    public void interact (Tile t){
        t.accept(this);
    }
    public void visit(Wall w){}
    public void visit(Empty e) {
        swapPositions(e.getPosition(), getPosition());
    }
    public abstract void visit(Enemy e);
    public abstract void visit(Player p);
    public String getName(){
        return name;
    }
    public abstract void tick();

}
