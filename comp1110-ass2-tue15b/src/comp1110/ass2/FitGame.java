package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static comp1110.ass2.Piece.intToString;

/**
 * This class provides the text interface for the IQ Fit Game
 * <p>
 * The game is based directly on Smart Games' IQ-Fit game
 * (https://www.smartgames.eu/uk/one-player-games/iq-fit)
 */
public class FitGame {

    /**
     * Determine whether a piece placement is well-formed according to the
     * following criteria:
     * - it consists of exactly four characters
     * - the first character is a valid piece descriptor character (b, B, g, G, ... y, Y)
     * - the second character is in the range 0 .. 9 (column)
     * - the third character is in the range 0 .. 4 (row)
     * - the fourth character is in valid orientation N, S, E, W
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        char firstChar = 0;
        char fourthChar = 0;
        int secondInt = 0;
        int thirdInt = 0;
        if (piecePlacement.length()!=4){
            return false;
        }else {
            firstChar = piecePlacement.charAt(0);
            fourthChar = piecePlacement.charAt(3);
            if (Character.isDigit(piecePlacement.charAt(1))){
                secondInt = Integer.parseInt(String.valueOf(piecePlacement.charAt(1)));
            }else return false;
            if (Character.isDigit(piecePlacement.charAt(2))){
                thirdInt = Integer.parseInt(String.valueOf(piecePlacement.charAt(2)));
            }else return false;
        }
        return secondInt <= 9 && thirdInt <= 4 && Piece.getColor(firstChar) != null && Piece.setDirection(fourthChar) != null;
    }// FIXME Task 2: determine whether a piece placement is well-formed.

    /**
     * Determine whether a placement string is outside the board:
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is outside; false if the piece placement is inside the board.
     */
    static boolean isPiecePlacementOutside(String piecePlacement){
        Set<String> pieces = new HashSet<>();
        Piece piece = new Piece(piecePlacement);
        pieces = piece.getOccupiedRange();
        String[] array = new String[pieces.size()];
        pieces.toArray(array);
        for (int i=0;i<array.length;i++){
            int col = Integer.parseInt(String.valueOf(array[i].charAt(0)), 16);
            int row = Integer.parseInt(String.valueOf(array[i].charAt(1)), 16);
            if (col<0 || col>9){
                return true;
            }else if (row<0 || row>4){
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 10);
     * - each piece placement is well-formed
     * - no shape appears more than once in the placement
     * - the pieces are ordered correctly within the string
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementWellFormed(String placement) {
        int pieceNum = placement.length()/4, spi=0;
        Set<String> piecePlace = new HashSet<>();
        Set<Character> pieceType = new HashSet<>();

        for(int i=0; i<pieceNum; i++) {
            char pieceCharAt0 = Character.toLowerCase(placement.charAt(spi));

            if(i<pieceNum-1) if (pieceCharAt0 > Character.toLowerCase(placement.charAt(spi + 4))) return false;
            if(Piece.getColor(pieceCharAt0)==null) return false;
            if(Piece.setDirection(placement.charAt(spi+3))==null) return false;
            if(('0' > placement.charAt(spi+1) || '9' < placement.charAt(spi+1)) || ('0' > placement.charAt(spi+2) || '9' < placement.charAt(spi+2))) return false;
            pieceType.add(pieceCharAt0);
            piecePlace.add(placement.substring(spi, spi + 4));
            if (!isPiecePlacementWellFormed(placement.substring(spi, spi + 4))) return false;
            spi += 4;
        }
        if(piecePlace.size()!=pieceNum || pieceType.size()!=pieceNum) return false;
        if (placement.length()%4>0 || placement.length()==0) return false;

        return true; // FIXME Task 3:
    }

    /**
     * Determine whether a placement string is valid.
     *
     * To be valid, the placement string must be:
     * - well-formed, and
     * - each piece placement must be a valid placement according to the
     *   rules of the game:
     *   - pieces must be entirely on the board
     *   - pieces must not overlap each other
     *
     * @param placement A placement string
     * @return True if the placement sequence is valid
     */
    public static boolean isPlacementValid(String placement) {
        int pieceNum = placement.length() / 4;
        boolean tag = true;
        tag = tag & isPlacementWellFormed(placement);
        if (tag==false){
            return tag;
        }
        Set<String> result = new HashSet<String>();
        Set<String> a1 = new HashSet<String>();

        for (int i = 0; i < pieceNum; i++) {
            String sub = placement.substring(i*4,i*4+4);
            Piece a= new Piece(sub);
            Set<String> a2 = a.getOccupiedRange();
            result.clear();
            result.addAll(a1);
            result.retainAll(a2);
            if(result.isEmpty()){
                a1.addAll(a2);
            }else {
                tag = false;
                break;
            }
            if (!isPiecePlacementWellFormed(placement.substring(i*4, i*4 + 4))||isPiecePlacementOutside(placement.substring(i*4, i*4 + 4))) tag = false;
        }
        //System.out.println("G80S"+isPiecePlacementOutside("G80S"));
        return tag;// FIXME Task 5: determine whether a placement string is valid
    }

    /**
     * Given a string describing a placement of pieces, and a location
     * that must be covered by the next move, return a set of all
     * possible next viable piece placements which cover the location.
     *
     * For a piece placement to be viable it must:
     *  - be a well formed piece placement
     *  - be a piece that is not already placed
     *  - not overlap a piece that is already placed
     *  - cover the location
     *
     * @param placement A starting placement string
     * @param col      The location's column.
     * @param row      The location's row.
     * @return A set of all viable piece placements, or null if there are none.
     */
    static Set<String> getViablePiecePlacements(String placement, int col, int row) {

        ArrayList<Piece> allPieces = initialPiecePosition(placement);
        ArrayList<Piece> allPiecesAtPoint = getAllPieceAtPoint(col,row);
        Set<String> rtn = new HashSet<>();
        State board = new State();

        for(int i=0; i<allPieces.size();i++){
            board.setPieceOccupied(allPieces.get(i));
        }

        allPiecesAtPoint.forEach(p->{
            if(checkPieceWithGivenPiece(p, allPieces) && board.checkPieceOccupied(p)) rtn.add(p.toString());
        });

        if(rtn.size()==0) return null;
        return rtn; // FIXME Task 6: determine the set of all viable piece placements given existing placements
    }
    static Set<String> getViablePiecePlacements(ArrayList<Piece> allPieces, int col, int row) {

        ArrayList<Piece> allPiecesAtPoint = getAllPieceAtPoint(col,row);
        Set<String> rtn = new HashSet<>();
        State board = new State();

        for(int i=0; i<allPieces.size();i++){
            board.setPieceOccupied(allPieces.get(i));
        }

        allPiecesAtPoint.forEach(p->{
            if(checkPieceWithGivenPiece(p, allPieces) && board.checkPieceOccupied(p)) rtn.add(p.toString());
        });

        if(rtn.size()==0) return null;
        return rtn;
    }

    /**
     * Return the solution to a particular challenge.
     **
     * @param challenge A challenge string.
     * @return A placement string describing the encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        /*


        for (int i=0;i<Games.SOLUTIONS.length;i++){
            if (challenge.equals(Games.SOLUTIONS[i].objective)){
                return Games.SOLUTIONS[i].placement;
            }
        }*/

        ArrayList<Piece> pieces = initialPiecePosition(challenge);
        Set<String> blackList = new HashSet<>();
        searchSolution(pieces,blackList);

        ArrayList<String> resultForSort = new ArrayList<>();
        pieces.forEach(t->{resultForSort.add(t.toString()); });
        Collections.sort(resultForSort,String.CASE_INSENSITIVE_ORDER);

        String rtn="";
        for(String v: resultForSort) rtn+=v;

        return rtn;
        // FIXME Task 9: determine the solution to the game, given a particular challenge
    }

    /**
     * search solution of a board
     * @param pieces all pieces given
     * @param blackList ignore pieses
     */

    public static void searchSolution(ArrayList<Piece> pieces,Set<String> blackList){

        if(pieces.size()==10) {
            System.out.println(pieces);
            return;
        }

        State board = new State();
        pieces.forEach(p-> board.setPieceOccupied(p));
        Set<String> vacuousPositions=board.getVacuousPositions();

        for(String vacuousPoint: vacuousPositions) {
            Set<String> potentialDot= getViablePiecePlacements(pieces, Piece.charToInt(vacuousPoint.charAt(0)), Piece.charToInt(vacuousPoint.charAt(1)));
            if(potentialDot!=null)
                for(String dot:potentialDot){
                    if(dot!=null) {
                        if (blackList.contains(dot)) return;
                        for(String v : blackList){
                            if(v.charAt(0)==dot.charAt(0))
                                return;
                        }

                        board.setPieceOccupied(dot);

                        if (!board.checkOrphan()) {
                            Piece dd = new Piece(dot);

                            pieces.add(dd);
                            ArrayList<String> resultForSort = new ArrayList<>();
                            for(Piece p : pieces){
                                resultForSort.add(p.toString());
                            }
                            Collections.sort(resultForSort,String.CASE_INSENSITIVE_ORDER);

                            if(!isPlacementWellFormed(pieceToString(resultForSort))){
                                blackList.add(dd.toString());
                                pieces.remove(dd);
                            }

                            blackList.add(dot);
                            searchSolution(pieces,blackList);
                            blackList.remove(dot);
                        }
                        board.removePieceOccupied(dot);

                    }
                }
        }

    }

    /**
     * Piece Arraylist to string
     * @author Gong Chen
     * @param pieces Arraylist of pieces
     * @return a placement
     */
    public static String pieceToString(ArrayList<String> pieces){
        String rtn="";
        for(String s:pieces){
            rtn+=s;
        }
        return rtn;
    }



    /**
     * Return the String of four occupied positions.
     **
     * @param placement The board of the game.
     * @return A String contains four occupied position from the placement String;
     */

    public static ArrayList<Piece> initialPiecePosition(String placement){
        if (placement.length()%4!=0){
            throw new IllegalArgumentException("Illegal Input");
        }
        int pieceNum = placement.length()/4, spi=0;
        ArrayList<Piece> pieces = new ArrayList<>();

        for(int i=0; i<pieceNum; i++) {
            spi = i * 4;
            char color =placement.charAt(spi);
            int positionX = Integer.parseInt(Character.toString(placement.charAt(spi+1)));
            int positionY = Integer.parseInt(Character.toString(placement.charAt(spi+2)));
            Piece.Direction direction = Piece.setDirection(placement.charAt(spi+3));
            //  Color type, int positionX, int positionY, Direction direction
            Piece p = new Piece(color,positionX,positionY,direction);
            pieces.add(p);
        }
        return pieces;
    }

    /**
     * Return the ArrayList of Set of occupied pieces positions.
     **
     * @param pieces Occupied pieces on the board.
     * @return A ArrayList of Set<String> of occupied pieces positions.
     */
    public static ArrayList<Set<String>> getOccupiedPositions(ArrayList<Piece> pieces){
        Piece[] pieceArray = (Piece[]) pieces.toArray();
        ArrayList<Set<String>> occupiedPositions = new ArrayList<>();
        for (int i=0;i<pieceArray.length;i++){
            occupiedPositions.add(pieceArray[i].getOccupiedRange());
        }
        return occupiedPositions;
    }

    /**
     * Return the Set of occupied pieces positions.
     **
     * @param occupiedPositions Sets of occupied pieces on the board.
     * @return A Set<String> of occupied pieces positions.
     */
    public static Set<String> getOccupiedPosition(ArrayList<Set<String>> occupiedPositions){
        Set<String>[] occupiedPositionArray = (Set<String>[]) occupiedPositions.toArray();
        Set<String> occupiedPosition = new HashSet<>();
        for (int i=0;i<occupiedPositionArray.length;i++){
            occupiedPosition.addAll(occupiedPositionArray[i]);
        }
        return occupiedPosition;
    }



    /**
     * Return a set of all pieces contains the certain point
     **
     * @param col of the point
     * @param row of the point
     * @return all pieces contains  the certain point
     */
    public static ArrayList<Piece> getAllPieceAtPoint(int col, int row){
        ArrayList<Piece> allPieces = new ArrayList<>();
        String pos = intToString(col, row);

        for(Piece.Type t : Piece.Type.values())
            for(Piece.Direction d: Piece.Direction.values())
                for(int r=0; r<5;r++)
                    for (int c=0;c<10;c++){
                        Piece p = new Piece(t.toString().charAt(0),c,r,d);
                        var range = p.getOccupiedRange();
                        if(range.contains(pos))
                            allPieces.add(p);
                    }
        return allPieces;
    }


    /**
     * Check same type or same colour of two piece used twice or a piece used twice
     **
     * @param p this piece features
     * @param allPieces other piece features by given
     * @return the condition of input, can or not put on board;
     */
    public static Boolean checkPieceWithGivenPiece(Piece p, ArrayList<Piece> allPieces){
        for(Piece allP : allPieces){
            if (p.getType()==allP.getType()) return false;
            if (Piece.getColor(p.getType())== Piece.getColor(allP.getType())) return false;
        }
        return true;
    }

}
