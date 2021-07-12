package comp1110.homework.J05;

import java.util.Scanner;

public class Grade {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int mark = in.nextInt();
        if (mark>=0 && mark<50){
            System.out.println("N");
        }else if (mark>=50 && mark<60){
            System.out.println("P");
        }else if (mark>=60 && mark<70){
            System.out.println("C");
        }else if (mark>=70 && mark<80){
            System.out.println("D");
        }else if (mark>=80 && mark<=100){
            System.out.println("HD");
        }else{
            System.out.println("Bad mark");
        }
    }
}
