package View.inputReader;

import java.util.Scanner;

public class ScannerReader implements InputReader{
    public char getInput() {
        Scanner s = new Scanner(System.in);
        char ans = s.next().charAt(0);
        while (!isValid(ans)) {
             ans = s.next().charAt(0);
         }
         return ans;
 }
     private boolean isValid(char c){
         return(c=='q' || c=='w' || c=='e' || c=='a'|| c=='s' || c=='d');
     }
 }

