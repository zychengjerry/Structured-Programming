package comp1110.lab5;

import java.util.ArrayList;
import java.util.Collections;

public class reverseArray {
    int[] originArray;
    int[] reverseArray;
    int length = 0;

    public void arrayReverse(){
        reverseArray = new int[length];
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < length; i++) {
            arraylist.add(originArray[i]);
        }
        Collections.reverse(arraylist);
        for (int i = 0; i < length; i++) {
            reverseArray[i] = (int) arraylist.get(i);
        }
    }

    public static void main(String[] args) {
        reverseArray a = new reverseArray();
    }

}
