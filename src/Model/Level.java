package Model;

import Model.Board.Board;
import Model.tile.Tile;
import Model.tile.units.Unit;
import Model.tile.units.enemies.Enemy;
import Model.tile.units.players.Player;

import java.util.List;

public class Level {
    private Board board;
    private int num;

    public Level(Board board, int num){
        this.board=board;
        this.num=num;
    }
    public void tick(){
        board.player().tick();
        for(Enemy enemy: board.enemies()){
            enemy.tick();
            if(!board.player().alive()){}
                //TODO end game
        }
    }
    public void run(){
        while(!board.enemies().isEmpty())
            tick();
        //TODO finish game
    }

}
