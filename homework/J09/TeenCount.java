package comp1110.homework.J09;

import java.util.function.IntPredicate;

public class TeenCount {
    public static int[] teenLess(int[] array){
        int length = 0;
        for (int i=0;i<array.length;i++){
            if(applyPredicate(array[i], x -> x < 13 || x > 19)){
                length++;
            }
        }
        int[] output = new int[length];
        int j=0;
        for (int i=0;i<array.length;i++){
            if(applyPredicate(array[i], x -> x < 13 || x > 19)){
                output[j]=array[i];
                j++;
            }

        }return output;

    }

    private static boolean applyPredicate(int value, IntPredicate predicate) {
        return predicate.test(value);
    }

}
