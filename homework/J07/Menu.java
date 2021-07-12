package comp1110.homework.J07;

import java.util.Scanner;

public class Menu {

    public static int findRange(int[] array){
        return array[array.length-1]-array[0];
    }

    public static int getMax(int[] array){
        return array[array.length-1];
    }

    public static int getSecondHighest(int[] array){
        return array[array.length-2];
        }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter:");
        System.out.println("1 to find range of the scores");
        System.out.println("2 to find the maximum score");
        System.out.println("3 to find the second highest score");
        String choose = in.nextLine();
        int chooseM = Integer.parseInt(choose);
        String arraySize = in.nextLine();
        int arraySize1 = Integer.parseInt(arraySize);
        int[] temp = new int[arraySize1];
        for (int i=0;i<arraySize1;i++){
            temp[i] = Integer.parseInt(in.next());
        }
        if (chooseM==1) {
            System.out.println(findRange(temp));
        }else if (chooseM==2){
            System.out.println(getMax(temp));
        }else if (chooseM==3){
            System.out.println(getSecondHighest(temp));
        }else {
            System.out.println("Invalid Option Selected");
        }
    }
}
