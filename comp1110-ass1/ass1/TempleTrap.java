package comp1110.ass1;

import java.util.*;

/**
 * This class represents a TempleTrap game, including the state of the
 * game and supporting methods that allow the game to be solved.
 *
 * The class cont
 * Temple Trap is children
 */
public class TempleTrap {
    static final int OFF_BOARD = -2;       // constant representing an off-board position
    static final int FINISH_POSITION = -1; // constant representing the finish position for the game

    /**
     * The objective represents the problem to be solved in this instance of the game.
     */
    Objective objective;

    /**
     * board is an instance field that is an array of length nine
     * with one position for each spot on the board.   Once initialized
     * the array will store the tile that is currently at each of the
     * nine board positions, with a null representing the position which
     * has no tile.
     *
     * If a Tile is in position 0, then board[0] holds a reference to that
     * Tile instance.  If a Tile is in position 3, then board[3] holds a
     * reference to that Tile instance. If the position is empty, that
     * element of the board is 'null'.
     */
    public Tile[] board = new Tile[9];

    /** The board position holding the peg */
    private int pegPosition;

    /**
     * Construct a game with a specific objective
     *
     * @param objective The objective of this game.
     */
    public TempleTrap(Objective objective) {
        this.objective = objective;
        initializeBoardState();
    }

    /**
     * Construct a game for a given level of difficulty.
     * This chooses a new objective and creates a new instance of
     * the game at the given level of difficulty.
     *
     * @param difficulty The difficulty of the game.
     */
    public TempleTrap(int difficulty) {
        this(Objective.newObjective(difficulty));
    }

    /** @return The Objective for the current TempleTrap instance. */
    public Objective getObjective() {  return objective; }

    /** @return the current peg position for this game */
    public int getPegPosition() { return pegPosition; }

    /** @param position The new peg position  */
    public void setPegPosition(int position) {  pegPosition = position; }
    /**
     * Initialise the board state according to the objective.
     */
    public void initializeBoardState() {
        String boardState = objective.getInitialState();
        for (int i = 0; i < boardState.length() / 2; i++) {
            String placement = boardState.substring(i * 2, ((i + 1) * 2));
            addTileToBoard(TileName.values()[i], placement);
        }
        pegPosition = Character.getNumericValue(boardState.charAt(boardState.length() - 1));
    }

    /**
     * Add a new tile placement to the board state, updating relevant
     * data structures accordingly.
     *
     * Note: this method is only used when initialising the board since the
     * tiles are never removed from the board.
     *
     * @param placement The placement to add.
     */
    private void addTileToBoard(TileName type, String placement) {
        Tile tile = new Tile(type, placement);
        int pos = tile.getPosition();
        board[pos] = tile;
    }

    /**
     * Print out tile state.  May be useful for debugging.
     */
    public void printTileState() {
        for (int i = 0; i < 9; i++) {
            System.out.print(board[i] == null ? "__" : board[i].toString() + "");
            if (i % 3 == 2) System.out.println();
        }
        System.out.println(getBoardState());
    }

    /**
     * This method may be useful for debugging.
     * @return A String representation of the board state of the current TempleTrap instance
     */
    public String getBoardState() {
        String rtn = "";
        for (int i = 0; i < 8; i++) {
            Tile t = Tile.getTileFromID(i);
            rtn += t == null ? "--" : t.getOrientation().toString() + t.position;
        }
        rtn += pegPosition;
        return rtn;
    }

    /**
     * Takes a solution string and returns the number of directions
     * characters in the solution (note that a solution string is made
     * up of tile symbols, the letter 'p' (for peg) and direction
     * letters.
     *
     * The number returned will match the number recorded in the upper
     * right of each game objective.  For example, objective 1 has
     * the number 11 in the upper right, and a correct solution string
     * for problem 1 will return the number 11 if passed to this
     * function.
     *
     * You may find this useful for the final (advanced) part of the
     * assignment, where you have to solve the game.
     *
     * @param solution a solution string (partial or complete)
     * @return the number of direction characters in the (partial solution).
     */
    public static int directionsInSolution(String solution) {
        int steps = 0;
        for (int i = 0; i < solution.length(); i++) {
            char c = solution.charAt(i);
            if (c >= 'E' && c <= 'W')
                steps++;
        }
        return steps;
    }

    /**
     * Takes a solution string and works out how many game steps
     * it contains (a game step may be a single tile move or a
     * single peg step -- note that a peg step is from one valid
     * position to the next, so may contain multiple directions if it
     * goes over green areas).
     *
     * You may find this useful for the final (advanced) part of the
     * assignment, where you have to solve the game.
     *
     * This method works both for complete solutions and partial solutions.
     *
     * @param solution a solution string
     * @return the number of game steps in the path
     */
    public static int stepsInSolution(String solution) {
        return solution.length() - directionsInSolution(solution);
    }

    /**
     * Assuming that the movement is valid, update the tile data structure
     * with a new tile and update the location field of that tile. The previous
     * location of the tile in the tile data structure should be set to null.
     *
     * Each entry in the data structure corresponds to a location, and
     * each location contains either a tile or is null.
     *
     * locations that are covered by a tile will have their data structure
     * entry point to the covering tile.
     *
     * locations that are not covered by a tile will point to null.
     *
     * @param tile  The Tile to be moved.
     * @param direction The direction in which to move the tile.
     */
    public void moveTile(Tile tile, Direction direction) {
        int pos = tile.getPosition();
        int newPos = getNextPosition(pos, direction);
        tile.position = newPos;
        board[newPos] = board[pos];
        board[pos] = null;
    }

    /**
     * Update the peg's position in the current game.
     * @param next the position the peg should be located in after running this method.
     */
    public void updatePeg(int next) {
        pegPosition = next;
    }

    /** START OF ASSIGNMENT TASKS */

    /**
     * Given a boardState, determine whether it is valid.
     * A boardState is valid under the following conditions:
     * - The string is exactly 17 characters long.
     * - Each orientation is a direction character (N, E, S, W)
     * - Each position is a number from 0 to 8
     * - No two tiles share a position.
     * - The peg is *not* placed on the vacant position.
     * - The peg is *not* located on an GREEN tile.
     *
     * @param boardState a string representing a boardState.
     * @return true if the boardState is valid, false if it is invalid.
     */
    public static boolean isBoardStateValid(String boardState) {
        if (boardState.length()!=17) {
            return false;
        } else {
            for (int i=0;i<=16;i++) {
                if (Character.isLetter(boardState.charAt(i))){
                    for (int j=0;j<=14;j=j+2) {
                        char cD = boardState.charAt(j);
                        switch (String.valueOf(cD)){
                            case "N":
                            case "E":
                            case "S":
                            case "W":
                                continue;
                            default:
                                return false;
                        }
                    }
                } else {
                    int intLast = Integer.parseInt(String.valueOf(boardState.charAt(16)));
                    int intP1 = Integer.parseInt(String.valueOf(boardState.charAt(1)));
                    int intP2 = Integer.parseInt(String.valueOf(boardState.charAt(3)));
                    int intP3 = Integer.parseInt(String.valueOf(boardState.charAt(5)));
                    if (intLast>8){
                        return false;
                    }else if (intLast==intP1 || intLast==intP2 || intLast==intP3){
                        return false;
                    }else {
                        for (int k=1;k<=15;k=k+2) {
                            char cP = boardState.charAt(k);
                            if (Integer.parseInt(String.valueOf(cP))>8){
                                return false;
                            }else {
                                for (int m=1;m<=15;m=m+2) {
                                    int cTiles = Integer.parseInt(String.valueOf(boardState.charAt(m)));
                                    for (int n=1;n<=15;n=n+2) {
                                        int cTiles1 = Integer.parseInt(String.valueOf(boardState.charAt(n)));
                                        if (cTiles-cTiles1==0) {
                                            if (m!=n) {
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        }return true;
                    }
                }
            }return true;
        }
    }// FIXME Task 4 (P)

    /**
     * Given a position and a direction to move in, determine the next position.
     *
     * @param pos the current position
     * @param dir the Direction to move in.
     * @return the next position if it is on the board, FINISH_POSITION if it is at the
     * finish position, or OFF_BOARD if it is off the board.
     */
    public static int getNextPosition(int pos, Direction dir) {
            if (dir==Direction.EAST){
                if (pos==2||pos==5||pos==8){
                    return OFF_BOARD;
                }
                return pos+1;
            }else if (dir==Direction.WEST){
                if (pos==3||pos==6){
                    return OFF_BOARD;
                }else if (pos==0){
                    return FINISH_POSITION;
                }
                return pos-1;
            }else if (dir==Direction.SOUTH){
                if (pos==6||pos==7||pos==8){
                    return OFF_BOARD;
                }
                return pos+3;
            }else if (dir==Direction.NORTH){
                if (pos==0||pos==1||pos==2){
                    return OFF_BOARD;
                }
                return pos-3;
            }else {
                return 0;
            }
    }// FIXME Task 6 (P)


    /**
     * Determine whether a given tile can be moved.
     *
     * A tile can only be moved if the following conditions are met:
     * - The Tile does not contain the Peg.
     * - The Tile is adjacent to an empty (null) space.
     *
     * @param tile the tile to be moved.
     * @return true if the tile can be moved, false otherwise
     */
    public boolean canMoveTile(Tile tile) {
        StringBuilder cTiles1 = new StringBuilder();
        StringBuilder cTile = new StringBuilder();
        for (int m=1;m<=15;m=m+2) {
            cTiles1.append(getBoardState().charAt(m));
        }
        for (int n=0;n<=8;n++){
            if (!String.valueOf(cTiles1).contains(String.valueOf(n))){
                cTile.append(n);
            }
        }

        if (tile.getPosition()==getPegPosition()){
            return false;
        }else {
            if (tile.getPosition() + 1== Integer.parseInt(String.valueOf(cTile))){
                return tile.getPosition() != 2 && tile.getPosition() != 5 && tile.getPosition() != 8;
            }else if (tile.getPosition() - 1 == Integer.parseInt(String.valueOf(cTile))){
                return tile.getPosition() != 3 && tile.getPosition() != 6;
            }else if (tile.getPosition() + 3 == Integer.parseInt(String.valueOf(cTile))){
                return tile.getPosition() != 6 && tile.getPosition() != 7 && tile.getPosition() != 8;
            }else if (tile.getPosition() - 3 == Integer.parseInt(String.valueOf(cTile))){
                return tile.getPosition() != 1 && tile.getPosition() != 2;
            }else return false;
        }
    }// FIXME Task 8 (CR)

    /**
     * Determine whether a Tile movement is valid.
     * A tile movement is valid under the following conditions:
     * - The Tile does not move off the board
     * - The Tile moves into an empty (null) space.
     * - The Tile being moved does not contain the Peg.
     *
     * @param tile The Tile to be moved.
     * @param direction the direction the tile is to be moved in.
     * @return true if the movement is valid, false if it is invalid.
     */
    public boolean isTileMovementValid(Tile tile, Direction direction) {
        //moveTile(tile, direction);
        int nextP = getNextPosition(tile.getPosition(),direction);
        StringBuilder cTiles1 = new StringBuilder();
        StringBuilder cTile = new StringBuilder();
        for (int m=1;m<=15;m=m+2) {
            cTiles1.append(getBoardState().charAt(m));
        }
        for (int n=0;n<=8;n++){
            if (!String.valueOf(cTiles1).contains(String.valueOf(n))){
                cTile.append(n);
            }
        }//From Task 8

        if (nextP==OFF_BOARD){
            return false;
        }else if (nextP==getPegPosition()){
            return false;
        }else if (canMoveTile(tile)){
            return nextP == Integer.parseInt(String.valueOf(cTile));
        }else return false;
    }// FIXME Task 9 (CR)

    /**
     * This method returns a path for one step of the peg from the starting tile to the
     * first valid stopping position from a specified exit.  The finishing point is considered
     * a valid stopping position.   The method starts the path by exiting the starting tile via
     * exit A if viaA is true, otherwise exiting via exit B.
     *
     * Note that this is a search problem, so the implementation of this method is most
     * likely recursive, which means that getPegPathToDestination() (where the destination is the
     * finish position) is probably best solved by calling
     * getPegPathStep() on viable neighbours.
     *
     * The format of the path is a series of direction characters 'N', 'E', 'S', 'W' followed
     * by a character representing the finishing position (either "F" to indicate the finish
     * was reached, or single digit (eg '0') representing position).  The direction characters
     * reflect the path to the next step (ie the path to the first tile on which the peg
     * may stop, including the finish).
     *
     * If there is no path to a valid position from this tile using the specified exit, return null.
     *
     * @param start The starting position for the traversal.
     * @param viaA  If true, leave the start tile via exit A, otherwise use exit B
     * @return a string representing the path to the next step for the peg (as a series of
     * directions and the endpoint), or null if there is no such path.
     */
    public String getPegPathStep(Tile start, boolean viaA) {
        StringBuilder pegPath = new StringBuilder();
        if (viaA){
            if (getNextPosition(start.position,start.exitAFaces())!=-2){
                Tile nextAT = board[getNextPosition(start.position,start.exitAFaces())];
                Direction nextAAOppo = nextAT.exitAFaces().getOpposite();
                Direction nextABOppo = nextAT.exitBFaces().getOpposite();
                if (start.exitAFaces()!=nextAAOppo&&start.exitAFaces()!=nextABOppo){
                    return null;
                }else {
                    if (!start.canTransit(nextAT)){
                            return null;
                    }else {
                        if (nextAT.canTakePeg()){
                            pegPath.append(start.exitAFaces());
                        }else {
                            if (nextAT.position==-1){
                                pegPath.append("F");
                                return String.valueOf(pegPosition);
                            }else  {
                                pegPath.append(start.exitAFaces());
                                getPegPathStep(viaA);
                            }
                        }
                    }
                    pegPath.append(nextAT.position);
                }
            }else return null;
        }else {
            if (getNextPosition(start.position,start.exitBFaces())!=-2){
                Tile nextBT = board[getNextPosition(start.position,start.exitBFaces())];
                Direction nextBAOppo = nextBT.exitAFaces().getOpposite();
                Direction nextBBOppo = nextBT.exitBFaces().getOpposite();
                if (start.exitBFaces()!=nextBAOppo&&start.exitBFaces()!=nextBBOppo){
                    return null;
                }else {
                    if (!start.canTransit(nextBT)){
                            return null;
                    }else {
                        if (nextBT.canTakePeg()){
                            pegPath.append(start.exitBFaces());
                        }else {
                            if (nextBT.position==-1){
                                pegPath.append("F");
                                return String.valueOf(pegPosition);
                            }else  {
                                pegPath.append(start.exitBFaces());
                                getPegPathStep(false);
                            }
                        }
                    }
                    pegPath.append(nextBT.position);
                }
            }else return null;
        }
        return String.valueOf(pegPath);
    }// FIXME Task 11 (D)

    /**
     * Return a path for one step of the peg from its current position.
     *
     * @param viaA if true, attempt the path via the tile's exit A, otherwise exit B
     * @return a string representing the path to the next step for the peg, or null if
     * there is no such path.
     */
    public String getPegPathStep(boolean viaA) {
        return getPegPathStep(board[pegPosition], viaA);
    }

    /**
     * Return the path to the finish position if one exists.
     *
     * Starting at position start, exit via exit A if viaA is true, otherwise
     * via exit B, and keep moving the peg for more steps as long as there is a valid way forward.
     * If there is a path for the peg that goes all the way to the
     * finish position, then return a string representing that path.  The
     * string should contain a series of direction characters 'N', 'E', 'S', 'W',
     * or should be null if there is no path to the finish.
     *
     * @param start The tile from which to start
     * @param dest The destination tile as a character '0' ... '8', or 'F' for the finish point.
     * @param viaA If true, start the search from exit A of the start tiles, otherwise use exit B.
     * @return A string representing the series of directions taken to reach the
     * finish position, or null if there is no path.
     */
    public String getPegPathToDestination(Tile start, char dest, boolean viaA) {
        return null;  // FIXME Task 12 (HD)
    }

    /**
     * Find the solutions to the game (the current TempleTrap object).
     *
     * Notice that this question is an advanced question and is entirely
     * optional.   You will need to use advanced data types and will
     * need to understand how to perform a search, most likely using
     * recursion, which is not covered until lecture unit C1.
     *
     * @return A set of strings, each representing a placement of all tiles,
     * which satisfies all of the game objectives.
     * eg: "hSaNeEpWWNW" translates to:
     * move tile `h` South, then tile `a` North, tile `e` East, then move peg `p` WEST, WEST, NORTH, WEST.
     */
    public Set<String> getSolutions() {
        return null; // FIXME Task 13 (HD)
    }
}
