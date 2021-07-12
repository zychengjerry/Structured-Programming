package comp1110.lab4;

import java.util.Scanner;

public class HeadsOrTails {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Dice two_sided_dice = new Dice(1,2);
        while (in.hasNext()){
            String headOrTail = in.nextLine();  //h,head; t,tail
            int result = two_sided_dice.rollDice();

            if (headOrTail.equals("h")||headOrTail.equals("t")){
                if (result==2){
                    System.out.println("Good guess!");
                }else {
                    System.out.println("Bad luck!");
                }
            }else {
                System.out.println("Unexpected input");
            }
        }

    }

}
