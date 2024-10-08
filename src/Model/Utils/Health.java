package Model.Utils;

public class Health {
    private int max;
    private int curr;

    public Health(int max) {
        this.max = max;
        this.curr = max;
    }
    public int takeDamage(int damage) {
        if(damage < 0) {
            return 0;
        }
        int lastCurr = curr;
        curr = Math.max(curr - damage, 0);
        return Math.min(lastCurr,damage);
    }
    public boolean isAlive(){
        return curr>0;
    }
    public void heal(){
        curr=max;
    }
    public void heal(int addition){
        curr = Math.min(max,curr+addition);
    }

    public void addMax(int max) {
        this.max += max;
    }
    public int getCurr(){
        return curr;
    }
    public int getMax(){
        return max;
    }
}
