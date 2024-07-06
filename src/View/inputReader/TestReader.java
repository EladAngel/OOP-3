package View.inputReader;

import java.util.Iterator;
import java.util.List;

public class TestReader implements InputReader{
    private static final char ERROR_CHAR='!';
    private Iterator<Character> iter;
    private int playerOrdinal;
    public TestReader(List<Character> list, int playerOrdinal){
        iter=list.iterator();
    }
    public char getInput(){
        if(iter.hasNext())
            return iter.next();
        return ERROR_CHAR;
    }
    public int getNum(){return playerOrdinal;}
}
