package comp1110.ass2;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class PieceCoverageTests {

    @Test
    public void TestGetOccupiedRange(){
        Piece p;
        Set<String> res;

        /*
        * Assume all piece are valid and they are not out of boundary, because Task 2 - 6 are examed
        *   1) T2: Piece are valid
        *   2) T5: the start point of the piece is correct, that means all dots of this piece are not not of boundary
        *
        * If they can get all current range of piece from 00 then, no problem at any other start point.
        *  because the get occupied is calculate by + or - start point X, Y alex.
        *  Under the Assumption, no matter what will replace to 00(i.e 14, 21, 53...),
        *  the occupied positions are relative to that point.
        *
        * The same type may has different color, so can test them at the same time.
        *
        * The sum of this testing is all types among all directions.
        *
        * All possible occupations are converged in this test.
        */

        //Test Directions N E S W with piece b r B o s p y O P R S Y
        //N
        //base case, add common dots first, then add special dots
        res= new HashSet<>();res.add("00");res.add("30");res.add("20");res.add("10");

        p = new Piece("b00N");
        res.add("01");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("r00N");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        res.remove("01");res.add("11");
        p = new Piece("o00N");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("s00N");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        res.remove("11");res.add("21");
        p = new Piece("p00N");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        res.remove("21");res.add("31");
        p = new Piece("y00N");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        p = new Piece("B00N");
        res.remove("31"); res.add("11"); res.add("31");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("O00N");
        res.remove("11"); res.remove("31"); res.add("01"); res.add("21");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("P00N");
        res.remove("21"); res.add("11");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("R00N");
        res.remove("11");  res.add("31");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("S00N");
        res.remove("01"); res.remove("31"); res.add("11"); res.add("21");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("Y00N");
        res.remove("11"); res.add("31");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        //E
        res= new HashSet<>();res.add("13");res.add("10");res.add("11");res.add("12");

        p = new Piece("b00E");
        res.add("00");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("r00E");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("o00E");
        res.remove("00");res.add("01");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("s00E");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("p00E");
        res.remove("01");res.add("02");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        res.remove("02");res.add("03");
        p = new Piece("y00E");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        p = new Piece("B00E");
        res.add("01");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("O00E");
        res.remove("01");res.remove("03");res.add("00");res.add("02");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("P00E");
        res.remove("02");res.add("01");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("R00E");
        res.remove("01");res.add("03");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("S00E");
        res.remove("00");res.remove("03");res.add("01");res.add("02");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("Y00E");
        res.remove("01");res.add("03");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        //S
        res= new HashSet<>();res.add("01");res.add("11");res.add("21");res.add("31");

        p = new Piece("b00S");
        res.add("30");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("r00S");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("o00S");
        res.remove("30");res.add("20");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("s00S");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("p00S");
        res.remove("20");res.add("10");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("y00S");
        res.remove("10");res.add("00");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        p = new Piece("B00S");
        res.add("20");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("O00S");
        res.remove("00"); res.remove("20");res.add("10");res.add("30");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("P00S");
        res.remove("10");res.add("20");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("R00S");
        res.remove("20");res.add("00");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("S00S");
        res.remove("00"); res.remove("30");res.add("10");res.add("20");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("Y00S");
        res.remove("20");res.add("00");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));


        //W
        res= new HashSet<>();res.add("00");res.add("01");res.add("02");res.add("03");

        p = new Piece("b00W");
        res.add("13");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("r00W");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("o00W");
        res.remove("13");res.add("12");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("s00W");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("p00W");
        res.remove("12");res.add("11");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("y00W");
        res.remove("11");res.add("10");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        p = new Piece("B00W");
        res.add("12");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("O00W");
        res.remove("10"); res.remove("12");res.add("11");res.add("13");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("P00W");
        res.remove("11");res.add("12");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("R00W");
        res.remove("12");res.add("10");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("S00W");
        res.remove("13"); res.remove("10");res.add("11");res.add("12");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("Y00W");
        res.remove("12");res.add("10");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));


        //Test Directions N E S W with piece in g n i l G I L N
        //N
        res= new HashSet<>();res.add("00");res.add("20");res.add("10");

        p = new Piece("g00N");
        res.add("11");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("n00N");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("i00N");
        res.remove("11");res.add("21");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("l00N");
        res.remove("21");res.add("01");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        p = new Piece("G00N");
        res.add("11");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("I00N");
        res.remove("01");res.add("21");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("L00N");
        res.remove("11");res.add("01");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("N00N");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        //E
        res= new HashSet<>();res.add("10");res.add("11");res.add("12");

        p = new Piece("g00E");
        res.add("01");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("n00E");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("i00E");
        res.remove("01");res.add("02");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("l00E");
        res.remove("02");res.add("00");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        p = new Piece("G00E");
        res.add("01");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("I00E");
        res.remove("00");res.add("02");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("L00E");
        res.remove("01"); res.add("00");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("N00E");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        //S
        res= new HashSet<>();res.add("01");res.add("11");res.add("21");

        p = new Piece("g00S");
        res.add("10");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("n00S");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("i00S");
        res.remove("10"); res.add("00");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("l00S");
        res.remove("00"); res.add("20");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        p = new Piece("G00S");
        res.add("10");res.add("20");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("I00S");
        res.remove("20");res.add("00");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("L00S");
        res.remove("10");res.add("20");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("N00S");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        //W
        res= new HashSet<>();res.add("00");res.add("01");res.add("02");

        p = new Piece("g00W");
        res.add("11");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("n00W");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("i00W");
        res.remove("11");res.add("10");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("l00W");
        res.remove("10");res.add("12");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));

        p = new Piece("G00W");
        res.add("11");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("I00W");
        res.remove("12");res.add("10");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("L00W");
        res.remove("11");res.add("12");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
        p = new Piece("N00W");
        assertTrue("Incorrect " +p.toString() +" occupied points: " + p.getOccupiedRange() + " ,Excepted get: "+res,p.getOccupiedRange().equals(res));
    }
}
