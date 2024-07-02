package Model.Utils;

public class Health {
    private int max;
    private int curr;

    public Health(int max) {
        this.max = max;
        this.curr = max;
    }
    public int takeDamage(int damage) {
        return 0;
        //implemet
    }
    public boolean isAlive(){
        return curr>0;
    }
    public void heal(){
        curr=max;
    }

    public void addMax(int max) {
        this.max += max;
    }
}
