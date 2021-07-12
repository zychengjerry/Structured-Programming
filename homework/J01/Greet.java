package comp1110.homework.J01;

import java.util.Scanner;

public class Greet {
    public static void main(String[] args) {
        //System.out.println("Hi ...!");
        Scanner in = new Scanner(System.in);
        String a = in.next();
        System.out.println("Hi "+a+"!");
    }
}
