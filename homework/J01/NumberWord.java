package comp1110.homework.J01;

import java.util.Scanner;

public class NumberWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //System.out.println("Enter a string:");
        String a = in.nextLine();
        //System.out.println("Enter a number:");
        int b = in.nextInt();
        System.out.println(b+", "+a);
    }
}
