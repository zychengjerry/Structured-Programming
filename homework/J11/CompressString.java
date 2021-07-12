package comp1110.homework.J11;

import java.util.*;

public class CompressString {

//  1st.Version

    public static String compress(String input){
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        for (int i=0;i<input.length();i++){
            char c = input.charAt(i);
            int value = 1;
            if (map.get(c)==null){
                map.put(c,1);
            }else {
                value = map.get(c) + 1;
                map.put(c,value);
            }
        }
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        String output = "";
        while (iterator.hasNext()){
            Map.Entry<Character, Integer> next = iterator.next();
            if (next.getValue()!=1){
                output = output + next.getKey().toString() + next.getValue().toString();
            }else {
                output = output + next.getKey().toString();
            }
        }
        return output;
    }


/*
//  2st.Version

    public static String compress(String input){
        char[] inputChar = input.toCharArray();
        int i = 0;
        int j = 0;
        while (i<inputChar.length && j<inputChar.length){
            inputChar[i++] = inputChar[j];
            int t = j;
            while (j<inputChar.length && inputChar[j]==inputChar[i-1]){
                j++;
            }
            if (j-t>1){
                for (char c:String.valueOf(j-t).toCharArray()){
                    inputChar[i++]=c;
                }
            }
        }
        return String.valueOf(i);
    }
*/

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(compress(input));
    }

}
