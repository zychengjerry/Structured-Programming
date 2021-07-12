package comp1110.homework.J05;

import java.util.Scanner;

public class Odd {
    static boolean isOdd(int X){
        return X % 2 != 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int X = in.nextInt();
        if (isOdd(X)){
            System.out.println(X + " is odd");
        }else{
            System.out.println(X + " is even");
        }
    }
}
