package comp1110.homework.J06;

import java.util.Scanner;

public class Permute {
    static boolean isPermute(String s1,String s2){
        if (s1.length()==s2.length()){
            char[] chars1 = s1.toCharArray();
            char[] chars2 = s2.toCharArray();
            sort(chars1);
            sort(chars2);
            String chars1String = String.valueOf(chars1);
            String chars2String = String.valueOf(chars2);
            return chars1String.equals(chars2String);
        }else {
            return false;
        }
    }

    public static void sort(char[] chars) {
        for (int i=0;i<chars.length-1;i++){
            for (int j=0;j<chars.length-1-i;j++){
                if (chars[j]>chars[j+1]){
                    char temp = chars[j];
                    chars[j] = chars[j+1];
                    chars[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        if (isPermute(s1,s2)){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }
}
