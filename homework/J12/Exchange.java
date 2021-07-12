package comp1110.homework.J12;

public class Exchange {
    public static <T> T[] swap(T[] a, int i, int j){
        //T [] b = a;
        T[] b = a.clone();
        if (i<b.length&&j<b.length&&i>=0&&j>=0){
            b[i] = a[j];
            b[j] = a[i];
            return b;
        }else return b;
    }
}
