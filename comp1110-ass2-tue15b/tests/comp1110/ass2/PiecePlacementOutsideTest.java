package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static comp1110.ass2.Games.SOLUTIONS;
import static comp1110.ass2.TestUtility.*;
import static org.junit.Assert.assertTrue;

public class PiecePlacementOutsideTest {
    // original duration < 0.15 sec -> 1 sec
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    private void test(String in, String invalid, boolean expected) {
        boolean out = FitGame.isPiecePlacementOutside(in);
        assertTrue("Input was '" + in + "', expected " + expected + " but got " + out + (invalid == "" ? "" : " (subsequence " + invalid + " is not valid)"), out == expected);
    }

    //3: g,i,l,n
    //4: b,o,p,r,s,y

    @Test
    public void isPieceOutsideRight() {
        test("G80S", "",true);
        test("b72N", "",true);
        test("i90S", "",true);
        test("B00W", "",false);
    }

    @Test
    public void isPieceOutsideDown() {
        test("o02W", "",true);
        test("B03S", "",false);
        test("L34E", "",true);
        test("n64S", "",true);
    }

    @Test
    public void isPieceOutsideRD() {
        test("S84E", "",true);
        test("g94S", "",true);

    }

}
