package comp1110.homework.J04;

import java.util.Scanner;

import static java.lang.Character.*;

public class ShiftCipher {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int shiftValue = in.nextInt();
        char[] strArr = input.toCharArray();
        for (int i = 0; i < strArr.length; i++) {
            if(isLetter(strArr[i])){
                //A-Z: 65-90;a-z: 97-122
                if (isUpperCase(strArr[i])){
                    if (((int)strArr[i]+shiftValue)<=90 && ((int)strArr[i]+shiftValue)>=65){
                        strArr[i] += shiftValue;
                    }else if (((int)strArr[i]+shiftValue)>90 && ((int)strArr[i]+shiftValue)<=115){
                        strArr[i] = (char) ((int)strArr[i] + shiftValue - 26);
                    }
                }else if (isLowerCase(strArr[i])){
                    if (((int)strArr[i]+shiftValue)<=122 && ((int)strArr[i]+shiftValue)>=97){
                        strArr[i] += shiftValue;
                    }else if (((int)strArr[i]+shiftValue)>122 && ((int)strArr[i]+shiftValue)<=147){
                        strArr[i] = (char) ((int)strArr[i] + shiftValue - 26);
                    }
                }
            }else{
                strArr[i] = strArr[i];
            }
        }
        System.out.println(strArr);
    }
}
