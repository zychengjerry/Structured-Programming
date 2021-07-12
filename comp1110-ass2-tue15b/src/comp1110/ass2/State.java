package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>State</h1>
 * Record the state of a board
 * <p>
 * Add/remove the piece into the board,
 * Detect the pieces occupied on the board.
 * Output status of boards.
 *
 * @author  Gong Chen
 * @version 2.0
 * @since   2020-09-30
 */
public class State {
    private final int ROW = 5;
    private final int COL = 10;
    public boolean[][] boardStatus; // false when initialized

    /**
     * Initial the board with 5 rows and 10 columns
     */
    public State(){
        boardStatus = new boolean[COL][ROW];
    }

    /**
     * Get all vacuous points of the current board.
     *
     * @return All vacuous points of current board Set<String>
     */
    public Set<String> getVacuousPositions(){
        Set<String> allVacuousPositions = new HashSet<>();
        for(int row=0;row<5;row++) {
            for (int col = 0; col < 10; col++) {
                if(!boardStatus[col][row]) allVacuousPositions.add(Piece.intToString(col,row));
            }
        }
        return allVacuousPositions;
    }

    /**
     * Check orphan point of the board, one point that its neighbourhoods are all be occupied by other pieces
     *
     * @return true has orphan point
     */
    public boolean checkOrphan(){
        for(int row=0;row<5;row++) {
            for (int col = 0; col < 10; col++) {
                int count=0;
                ArrayList<String> neighbourhoods = new ArrayList<>();
                //up point
                if(row-1>=0) neighbourhoods.add(Piece.intToString(col,row-1));
                //left point
                if(col-1>=0) neighbourhoods.add(Piece.intToString(col-1,row));
                //right point
                if(col+1<10) neighbourhoods.add(Piece.intToString(col+1,row));
                //down point
                if(row+1<5) neighbourhoods.add(Piece.intToString(col,row+1));

                for(String point:neighbourhoods){
                    if(boardStatus[Piece.charToInt(point.charAt(0))][Piece.charToInt(point.charAt(1))] && !boardStatus[col][row])
                        count++;
                }

                if(count==neighbourhoods.size()) return true;
            }
        }
        return false;
    }

    /**
     * Set a dot into the board, notates its occupation
     * @param col the row of board
     * @param row the column of board
     */
    public void setOccupied(int col, int row){
        boardStatus[col][row] = true;
    }

    /**
     * Remove a dot out of the board, notates its vacuous
     * @param col the row of board
     * @param row the column of board
     */
    public void removeOccupied(int col, int row){
        boardStatus[col][row] = false;
    }

    /**
     * Put the piece into the board, set all dots it occupied are true
     * @param p a piece
     */
    public void setPieceOccupied(Piece p){
        Set<String> allPos = p.getOccupiedRange();
        for(String s : allPos) {
            int col = Piece.charToInt(s.charAt(0));
            int row = Piece.charToInt(s.charAt(1));
            setOccupied(col,row);
        }
    }

    /**
     * Put the piece into the board,
     * @param p a piece, p is string format
     */
    public void setPieceOccupied(String p){
        Piece piece = new Piece(p);
        Set<String> allPos = piece.getOccupiedRange();
        for(String s : allPos) {
            int col = Piece.charToInt(s.charAt(0));
            int row = Piece.charToInt(s.charAt(1));
            setOccupied(col,row);
        }
    }

    /**
     * Remove the piece out of the board
     * @param p 4_character_length represent a piece
     */
    public void removePieceOccupied(String p){
        Piece piece = new Piece(p);
        Set<String> allPos = piece.getOccupiedRange();
        for(String s : allPos) {
            int col = Piece.charToInt(s.charAt(0));
            int row = Piece.charToInt(s.charAt(1));
            removeOccupied(col,row);
        }
    }

    /**
     * get the whole possible pieces, set ok return true, collapse return false
     * @param p a pieces
     * @return the results of check
     */
    public boolean checkPieceOccupied(Piece p){
        Set<String> allPos = p.getOccupiedRange();
        for(String s : allPos) {
            int col = Piece.charToInt(s.charAt(0));
            int row = Piece.charToInt(s.charAt(1));
            if(outOfBoardCheck(col,row) || getPoint(col,row)) return false;
        }
        return true;
    }

    /**
     * get the whole possible pieces, set ok return true, collapse return false
     * @param p a pieces, p is string format
     * @return the results of check
     */
    public boolean checkPieceOccupied(String p){
        Piece piece = new Piece(p);
        Set<String> allPos = piece.getOccupiedRange();
        for(String s : allPos) {
            int col = Piece.charToInt(s.charAt(0));
            int row = Piece.charToInt(s.charAt(1));
            if(outOfBoardCheck(col,row) || getPoint(col,row)) return false;
        }
        return true;
    }


    /**
     * get a dot of the board
     *
     * @param col the row of dot
     * @param row the column of dot
     * @return status of the the dot on board
     */
    public boolean getPoint(int col, int row){
        return boardStatus[col][row];
    }

    /**
     * Check the dot is in the range of board
     *
     * @param col the row of dot
     * @param row the column of dot
     * @return true for occupied
     */
    public boolean outOfBoardCheck(int col, int row){
        return (col<0 || col>9||row<0||row>4);
    }

    /**
     * Print all status of the board
     *
     * @return The string of board status
     */
    @Override
    public String toString(){
        String rtn = "";
        for(int i=0;i<5;i++) {
            for (int j = 0; j < 10; j++) {
                rtn += " " + boardStatus[j][i];
            }
            rtn+="\n";
        }
        return rtn;
    }

}

