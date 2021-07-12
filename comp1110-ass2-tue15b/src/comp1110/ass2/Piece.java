package comp1110.ass2;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>Piece</h1>
 * The property of a piece
 * <p>
 * Constructs a piece by type, column, row and direction,
 * Encode piece color
 * Encode piece occupation range of a piece
 *
 * @author  Gong Chen, Yitao Chen, Zheyuan Cheng
 * @version 2.0
 */
public class Piece{
    private char type; //Case Sensitive
    private int column;
    private int row;
    private Direction direction;

    public enum Direction {N,S,W,E}
    public enum Color {blue, green, indigo, lime_green, navy_blue,
        orange, pink, red, sky_blue, yellow}
    public enum Type {b, B, g, G, i,
        I, l, L, n, N,o,O,p,P,r,R,s,S,y,Y}

    /**
     * The piece constructor initial the piece by String
     * @param placement The string of placement
     */
    public Piece(String placement){
        this.type = placement.charAt(0);
        this.column = charToInt(placement.charAt(1));
        this.row = charToInt(placement.charAt(2));
        this.direction = setDirection(placement.charAt(3));
    }

    /**
     * the another constructor used with "initialPiecePosition(placement)"
     * The piece constructor initial the piece by is features
     *
     * @param c type of a piece
     * @param col col of a piece
     * @param row col of a piece
     * @param d direction of a piece
     */
    public Piece(char c, int col, int row, Direction d){
        this.type = c;
        this.column = col;
        this.row = row;
        this.direction = d;
    }


    /**
     * Get the type of a piece
     * @return The type of a piece
     */
    public char getType(){
        return this.type;
    }

    /**
     * Get the column of a piece
     * @return The column of a piece
     */
    public int getColumn(){
        return column;
    }

    /**
     * Get the row of a piece
     * @return The row of a piece
     */
    public int getRow(){
        return row;
    }

    /**
     * @return The direction of a piece
     */
    public Direction getDirection(){
        return direction;
    }

    /**
     * Convert a character of direction to enum direction
     *
     * @param direction a character of direction
     * @return  the direction of piece
     */
    public static Direction setDirection(char direction){
        if(direction == 'N')
            return Direction.N;
        if(direction == 'E')
            return Direction.E;
        if(direction == 'S')
            return Direction.S;
        if(direction == 'W')
            return Direction.W;
        return null;
    }

    /**
     * Helper function turn string to int.
     **
     * @param c Character of integer
     * @return A integer number of that char
     */
    public static int charToInt(char c) {
        return c - 48;
    }

    /**
     * Get the piece color
     * Convert a color character to enum Color
     *
     * @param color the character of piece color
     * @return enum of color
     */
    public static Color getColor(char color) {
        if(color == 'B' || color == 'b')
            return Color.blue;
        if(color == 'G' || color == 'g')
            return Color.green;
        if(color == 'I' || color == 'i')
            return Color.indigo;
        if(color == 'L' || color == 'l')
            return Color.lime_green;
        if(color == 'N' || color == 'n')
            return Color.navy_blue;
        if(color == 'O' || color == 'o')
            return Color.orange;
        if(color == 'P' || color == 'p')
            return Color.pink;
        if(color == 'R' || color == 'r')
            return Color.red;
        if(color == 'S' || color == 's')
            return Color.sky_blue;
        if(color == 'Y' || color == 'y')
            return Color.yellow;
        return null;
    }

    /**
     * Helper function turn int to string, used for translate column and row from integer to a string.
     * @param col the first number
     * @param row the second number
     * @return A string of integer number;
     */
    public static String intToString(int col, int row){
        String s1 = "";
        if (col < 0) col += 16;
        s1+=Integer.toHexString(col);
        if (row < 0) row += 16;
        s1+=Integer.toHexString(row);
        return s1;
    }

    /**
     * Give all occupied dots in the board of a piece occupied
     * @return a set of all dots it occupied. ie. L02W should return 02,12,03,04,14
     */

    public Set<String> getOccupiedRange(){

        char t = this.type;
        int  c = this.column;
        int  r = this.row;
        Direction d = this.direction;
        Set<String> range = new HashSet<>();
        String dot = "";
        String dotS = "";

        //main body dots
        //b,o,p,r,s,y
        if(t=='b'||t=='B'||t=='o'||t=='O'||t=='p'||t=='P'||t=='r'||t=='R'||t=='s'||t=='S'||t=='y'||t=='Y') {
            for (int i = 0; i < 4; i++) {
                switch (d) {
                    case N:  dot = intToString(c + i, r); break;
                    case E:  dot = intToString(c + 1, r+i); break;
                    case S:  dot = intToString(c + i, r+1); break;
                    case W:  dot = intToString(c, r + i); break;
                }
                range.add(dot);
            }

        }
        //g,i,l,n
        if(t=='g'||t=='G'||t=='i'||t=='I'||t=='l'||t=='L'||t=='n'||t=='N') {
            for (int i = 0; i < 3; i++) {
                switch (d) {
                    case N:  dot = intToString(c + i, r); break;
                    case E:  dot = intToString(c + 1, r+i); break;
                    case S:  dot = intToString(c + i, r+1); break;
                    case W:  dot = intToString(c , r + i); break;
                }
                range.add(dot);
            }
        }

        //Make up lowercase dots
        if(t=='b'|| t=='r'|| t=='O'|| t=='P'|| t=='R') {
            switch (d) {
                case N:  dot = intToString(c , r+1); break;
                case E:  dot = intToString(c, r); break;
                case S:  dot = intToString(c + 3, r); break;
                case W:  dot = intToString(c +1, r +3); break;
            }
            range.add(dot);

            //Special dot in O
            //Special dot in P
            //Special dot in R
            switch (d) {
                case N:
                    if(t=='O') dotS = this.intToString(c +2 , r+1);
                    if(t=='P') dotS = this.intToString(c + 1, r+1);
                    if(t=='R') dotS = this.intToString(c +3 , r+1);
                    break;
                case E:
                    if(t=='O') dotS = this.intToString(c  , r+2);
                    if(t=='P') dotS = this.intToString(c  , r+1);
                    if(t=='R') dotS = this.intToString(c  , r+3);
                    break;
                case S:
                    if(t=='O') dotS = this.intToString(c +1 , r);
                    if(t=='P') dotS = this.intToString(c + 2, r);
                    if(t=='R') dotS = this.intToString(c  , r);
                    break;
                case W:
                    if(t=='O') dotS = this.intToString(c +1 , r+1);
                    if(t=='P') dotS = this.intToString(c +1 , r+2);
                    if(t=='R') dotS = this.intToString(c +1 , r);
                    break;
            }
            if(!(t=='b'||t=='r'))range.add(dotS);
        }

        if( t=='o' || t=='s'||t=='S') {
            switch (d) {
                case N:  dot = this.intToString(c +1 , r+1); break;
                case E:  dot = this.intToString(c, r+1); break;
                case S:  dot = this.intToString(c + 2, r); break;
                case W:  dot = this.intToString(c +1, r +2); break;
            }
            range.add(dot);

            //Special dot in S
            if(t=='S') {
                switch (d) {
                    case N: dotS = this.intToString(c+2 , r +1);break;
                    case E: dotS = this.intToString(c, r+2 );break;
                    case S: dotS = this.intToString(c + 1, r); break;
                    case W: dotS = this.intToString(c + 1, r + 1);break;
                }
                range.add(dotS);
            }
        }

        if(t=='g' || t=='n'||t=='G') {
            switch (d) {
                case N:  dot = this.intToString(c +1 , r+1); break;
                case E:  dot = this.intToString(c, r+1); break;
                case S:  dot = this.intToString(c + 1, r); break;
                case W:  dot = this.intToString(c +1, r +1); break;
            }
            range.add(dot);

            //Special dot in G
            if(t=='G') {
                switch (d) {
                    case N: dotS = this.intToString(c, r+1 );break;
                    case E: dotS = this.intToString(c, r );break;
                    case S: dotS = this.intToString(c + 2, r);break;
                    case W: dotS = this.intToString(c + 1, r + 2);break;
                }
                range.add(dotS);
            }
        }

        if(t=='i' || t=='I' ||t=='L' || t=='N') {
            //common dot in i I L N
            switch (d) {
                case N:  dot = this.intToString(c +2 , r+1); break;
                case E:  dot = this.intToString(c, r+2); break;
                case S:  dot = this.intToString(c , r); break;
                case W:  dot = this.intToString(c +1, r); break;
            }
            range.add(dot);

            //Special dot in I
            //Special dot in L
            //Special dot in N
            switch (d) {
                case N:
                    if(t=='I') dotS = this.intToString(c +1 , r+1);
                    if(t=='L'||t=='N') dotS = this.intToString(c  , r+1);
                    break;
                case E:
                    if(t=='I') dotS = this.intToString(c  , r+1);
                    if(t=='L'||t=='N') dotS = this.intToString(c  , r);
                    break;
                case S:
                    if(t=='I') dotS = this.intToString(c +1 , r);
                    if(t=='L'||t=='N') dotS = this.intToString(c+2, r);
                    break;
                case W:
                    if(t=='I') dotS = this.intToString(c +1 , r+1);
                    if(t=='L'||t=='N') dotS = this.intToString(c+1, r+2);
                    break;
            }
            if(t!='i')range.add(dotS);
        }

        if(t=='l') {
            switch (d) {
                case N:  dot = intToString(c , r+1); break;
                case E:  dot = intToString(c, r); break;
                case S:  dot = intToString(c+2 , r); break;
                case W:  dot = intToString(c +1, r+2); break;
            }
            range.add(dot);
        }

        if(t=='p') {
            switch (d) {
                case N:  dot = this.intToString(c+2 , r+1); break;
                case E:  dot = this.intToString(c, r+2); break;
                case S:  dot = this.intToString(c+1 , r); break;
                case W:  dot = this.intToString(c +1, r+1); break;
            }
            range.add(dot);
        }

        if(t=='y'||t=='B'||t=='Y') {
            switch (d) {
                case N:  dot = this.intToString(c+3 , r+1); break;
                case E:  dot = this.intToString(c, r+3); break;
                case S:  dot = this.intToString(c , r); break;
                case W:  dot = this.intToString(c +1, r); break;
            }
            range.add(dot);

            //Special dot in B
            //Special dot in Y
            switch (d) {
                case N:
                    if(t=='B') dotS = this.intToString(c +1 , r+1);
                    if(t=='Y') dotS = this.intToString(c +2 , r+1);
                    break;
                case E:
                    if(t=='B') dotS = this.intToString(c  , r+1);
                    if(t=='Y') dotS = this.intToString(c  , r+2);
                    break;
                case S:
                    if(t=='B') dotS = this.intToString(c +2 , r);
                    if(t=='Y') dotS = this.intToString(c+1, r);
                    break;
                case W:
                    if(t=='B') dotS = this.intToString(c +1 , r+2);
                    if(t=='Y') dotS = this.intToString(c+1, r+1);
                    break;
            }
            if(t!='y')range.add(dotS);
        }
        return range;
    }

    @Override
    public String toString(){
        return ""+type+column+row+direction;
    }

}
