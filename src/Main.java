import java.io.IOException;

public class Main {
  public static void main(String[] args) {
        System.out.println("Press any key to continue...");

        try {
            // Read a single byte/character
            int ch = System.in.read();

            // Cast the byte to char
            char c = (char) ch;

            System.out.println("You pressed: " + c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}