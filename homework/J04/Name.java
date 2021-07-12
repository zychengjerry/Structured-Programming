package comp1110.homework.J04;

import java.util.Scanner;

public class Name {
    public static void main(String[] args) {
        //String name = "";
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        if(name == null){
            System.out.println("");
        }else{
            String nameUp = name.toUpperCase();
            System.out.println(nameUp + "\n" + name.length());
        }
    }
}
