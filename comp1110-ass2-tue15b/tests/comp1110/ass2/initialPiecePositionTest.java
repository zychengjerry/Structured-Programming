package comp1110.ass2;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class initialPiecePositionTest {
    private void test(String in, ArrayList<String> expected) {

        String out = FitGame.initialPiecePosition(in).toString();
        assertTrue("Input was '" + in + "', expected " + expected + " but got " + out, out.equals(expected));
    }
    @Test
    public void initialPiecePositionTest(){
        String testString = "l53N";
        ArrayList<String> str = new ArrayList<String>();
        str.add("l53N");
        test(testString,str);
    }
    /*
    @Test
    public void illegalInputTest() {//throws IllegalArgumentException
        String in = "B84Sg62Ni21Wl02Nn93No64EP02WR33ES61NY4";
        IllegalArgumentException a = new IllegalArgumentException("Illegal Input");
        FitGame.initialPiecePosition(in);
    }*/

    @Test
    public void initialPiecePositionTest1(){
        String testString = "B71WG03Ei00Nl53N";
        ArrayList<String> str = new ArrayList<String>();
        str.add("B71W");
        str.add("G03E");
        str.add("i00N");
        str.add("l53N");
        test(testString,str);
    }

}