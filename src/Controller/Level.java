package Controller;

import Model.Board.Board;
import Model.tile.units.enemies.Enemy;
import View.OutPut.MessageCallBack;

public class Level {
    private final Board board;
    private int num;
    private MessageCallBack mc;

    public Level(Board board, int num){
        this.board=board;
        this.num=num;
    }
    public void tick(){
        mc.send(board.getPlayer().getDescription());
        board.getPlayer().tick();
        for(Enemy enemy: board.enemies()){
            enemy.tick();
            if(!board.getPlayer().alive()){}
                break;
        }
        mc.send(board.toString());
        mc.send("");

    }
    public void run(){
        while(!board.enemies().isEmpty()&&board.getPlayer().alive())
            tick();
        //TODO finish level
    }
    public int getNum(){
        return num;
    }
    public Board getBoard(){
        return board;
    }

}
