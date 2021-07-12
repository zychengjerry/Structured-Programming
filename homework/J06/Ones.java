package comp1110.homework.J06;

import java.sql.Time;
import java.util.Scanner;

public class Ones {
    public static int Times(String a, String b){
        int times = 0;
        for (int i = 0;i<b.length();i++){
            int j = b.indexOf(a,i);
            if (j==i){
                times++;
            }
        }
        return times;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        String numberBinary = Integer.toBinaryString(number);
        //Times("1",numberBinary);
        System.out.println(Times("1",numberBinary));
    }
}
