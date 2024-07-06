package Model.Utils.Generators;

public class FixedGenerator implements Generator {
    public FixedGenerator() {}

    public int generate(int value) {
        return value;
    }
    public int getRandomSign(){
        return 1;
    }
}
