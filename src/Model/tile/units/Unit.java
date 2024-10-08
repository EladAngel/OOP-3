package Model.tile.units;
import Model.Board.SemiBoard;
import Model.tile.Tile;
import Model.Utils.Health;
import Model.Utils.Position;
import Model.Utils.Generators.Generator;
import Model.tile.Wall;
import Model.tile.Empty;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;
import View.OutPut.MessageCallBack;

public abstract class Unit extends Tile {
    protected int attack;
    protected int defense;
    protected Health HP;
    protected String name;
    protected Generator generator;
    protected MessageCallBack messageCallBack;

    public Unit(int attack, int defense, int HP, String name, char ch, Position pos,Generator generator, SemiBoard semiBoard, MessageCallBack mc) {
        super(ch,pos,semiBoard);
        this.attack=attack;
        this.defense=defense;
        this.HP=new Health(HP);
        this.name=name;
        this.generator=generator;
        this.messageCallBack=mc;
    }
    public int attack(){
        return generator.generate(attack);
    }
    public int defense(){
        return generator.generate(defense);
    }
    public void battle(Unit enemy){
        messageCallBack.send(getName()+" engaged in combat with "+enemy.getName()+".");
        messageCallBack.send(getDescription());
        messageCallBack.send(enemy.getDescription());
        int Attack=this.attack();
        messageCallBack.send(getName()+ " rolled "+Attack+" attack points.");
        int Defense=this.defense();
        messageCallBack.send(enemy.getName()+ " rolled "+Defense+" defense points.");
        int damageTaken = enemy.takeDamage(Attack-Defense);
        messageCallBack.send(getName()+" dealt "+damageTaken+" damage to "+enemy.getName());
    }
    public int takeDamage(int damage){
        return HP.takeDamage(damage);
    }
    public boolean alive(){return HP.isAlive();}
    public void interact (Tile t){
        t.accept(this);
    }
    public void visit(Wall w){}
    public void visit(Empty e) {
        this.swapPositions(e);
    }
    public abstract void visit(Enemy e);
    public abstract void visit(Player p);
    public String getName(){
        return name;
    }
    public int health(){return HP.getCurr(); }
    public abstract void tick();
    public String getDescription(){
        return getName()+"         "+"Health: "+HP.getCurr()+"/"+HP.getMax() + "         "+"Attack: "+ attack + "         "+"Defense: "+ defense;
    }
}
