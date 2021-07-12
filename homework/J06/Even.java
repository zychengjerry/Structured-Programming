package comp1110.homework.J06;

import java.util.Scanner;

public class Even {
    static boolean isEven(int X){
        return (X+2) % 2 == 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for (int i = 1; i <= n; i++){
            if (isEven(i)){
                System.out.println(i);
            }
        }
    }
}
