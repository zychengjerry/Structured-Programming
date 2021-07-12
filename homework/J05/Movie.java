package comp1110.homework.J05;

import java.util.Scanner;

public class Movie {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        int time = in.nextInt();
        int timeM = time%60;
        int timeH = time/60;
        if (time==1){
            System.out.println(name + " runs for " + time + " minute");
        }else if (time>1 && time<60){
            System.out.println(name + " runs for " + time + " minutes");
        }else if (time==60){
            System.out.println(name + " runs for " + time/60 + " hour");
        } else if (time>60 && timeM==0){
            System.out.println(name + " runs for " + time/60 + " hours");
        }else if (time > 60 && time < 120){
            System.out.println(name + " runs for " + 1 + " hour and " + timeM + " minutes");
        }else{
            System.out.println(name + " runs for " + timeH + " hours and " + timeM + " minutes");
        }
    }
}
