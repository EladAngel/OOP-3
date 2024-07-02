package Model.Utils;

public class Position {
    protected int x;
    protected int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public double getDistance(Position pos) {
        return Math.sqrt(Math.pow(pos.x - this.x, 2) + Math.pow(pos.y - this.y, 2));
    }
}
