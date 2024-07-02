package Model.Utils.Generators;

import java.util.Random;
public class randomGenerator implements Generator{
    private Random rand;
    public randomGenerator() {
        rand = new Random();
    }
    public int generate(int n){
       return rand.nextInt(n);
    }
}
