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
        if(!enemy.alive()){
            kill(enemy);
        }
    }
    public int takeDamage(int damage){
        return HP.takeDamage(damage);
    }
    public boolean alive(){return HP.isAlive();}
    public abstract void kill(Unit u);
    public abstract void death();
    public void interact (Tile t){
        t.accept(this);
    }
    public void visit(Wall w){}
    public void visit(Empty e){
        swapPositions(e.getPosition(),getPosition());
    }
    public void swapPositions(Position p1, Position p2){
        Position p = p1;
        p1 = p2;
        p2 = p;
    }
    public abstract void visit(Enemy e);
    public abstract void visit(Player p);
    public String getName(){
        return name;
    }
}
