package comp1110.homework.J06;

import java.util.Scanner;

public class Prime {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        for (int i = 2; i <= number; i++){
            int j = 2;
            while (i % j != 0){
                j++;
            }
            if (j==i){
                System.out.println(i);
            }
        }
    }
}
