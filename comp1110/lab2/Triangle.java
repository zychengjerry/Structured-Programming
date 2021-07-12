package comp1110.lab2;

import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        for(int i=1;i<height;i++) {
            for (int j = height-1; i <= j; j--)
                System.out.print(" ");
            System.out.print("*");
            for (int j1 = 1; j1 <= 2*i-3; j1++)
                System.out.print(" ");
            if(i==1){
                System.out.print("\n");
            }else{
                System.out.print("*"+"\n");
            }
        }
        for(int k=0; k < height*2-1; k++){
            System.out.print("*");
        }
        System.out.println();
    }
}
