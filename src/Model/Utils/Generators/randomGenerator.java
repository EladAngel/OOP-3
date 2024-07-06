package Model.Utils.Generators;

import java.util.Random;
public class randomGenerator implements Generator{
    private Random rand;
    public randomGenerator() {
        rand = new Random();
    }
    public int generate(int n){
        if(n<=0)return 0;
       return rand.nextInt(n);
    }
    public int getRandomSign(){
        return rand.nextBoolean() ? 1 : -1;
    }
}
