package View.inputReader;

import java.util.Iterator;
import java.util.List;

public class TestReader implements InputReader{
    private static final char EROR_CHAR='!';
    private Iterator<Character> iter;
    public TestReader(List<Character> list){
        iter=list.iterator();
    }
    public char getInput(){
        if(iter.hasNext())
            return iter.next();
        return EROR_CHAR;
    }
}
