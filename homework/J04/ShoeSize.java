package comp1110.homework.J04;

import java.util.Scanner;
//import java.lang.Math;

public class ShoeSize {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double height = in.nextDouble();
        height = height * 5.0;
        float height1 = (float) height;
        height = Math.round(height);
        int heightInt = (int) height;
        System.out.println(heightInt);
    }
}
