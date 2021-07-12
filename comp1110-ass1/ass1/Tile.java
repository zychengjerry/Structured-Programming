package comp1110.ass1;

/**
 * This class represents a movable tile in the TempleTrap game.   The
 * class encodes which sort of tile it is (tileName), its orientation,
 * its position on the board, and whether it currently holds the peg
 * (AKA the 'adventurer').
 *
 * This class contains four tasks of varying difficulty that you need
 * to complete.
 */
public class Tile {

    private final TileName tileName;      // The TileName ('PLUS', 'SQUARE' ...) (this never changes)
    private final Direction orientation;  // The tile's orientation (this does not change after the board has been initialised)
    public int position;                  // The current position of the tile on the board.

    private static Tile[] list = new Tile[TileName.values().length];

    /**
     * Static method to return the tile with give tild ID
     * @param id A tile ID corresponding to the tileName ordinal
     * @return The corresponding tile
     */
    public static Tile getTileFromID(int id) {
        assert id >= 0 && id <= TileName.values().length;
        return list[id];
    }

    /**
     * Constructor for a Tile
     *
     * @param type The TileType of the new tile
     * @param placement the String placement of the tile eg: "N0"
     */
    public Tile(TileName type, String placement) {
        this.tileName = type;
        this.orientation = placementToOrientation(placement);
        this.position = placementToPosition(placement);
        list[tileName.ordinal()] = this;
    }

    /**
     * Given a two-character tile placement string, decode the tile's position.
     *
     * You will need to read the description of the encoding in README.md.
     *
     * Hint: you will probably want to use the charAt() method on the placemen
     * string.
     *
     * @param placement A string representing the placement of a tile on the game board
     * @return An int corresponding to the tile's position on the board.
     */
    public static int placementToPosition(String placement) {
        return Integer.parseInt(placement.substring(1));  // FIXME Task 1 (P)
    }

    /**
     * Given a two-character tile placement string, decode the tile's orientation.
     *
     * You will need to read the description of the encoding in README.md
     *
     * @param placement A string representing the placement of a tile on the game board
     * @return A value of type `Direction` corresponding to the tile's orientation on board
     */
    public static Direction placementToOrientation(String placement) {
        switch (String.valueOf(placement.charAt(0))) {
            case "W":
                return Direction.WEST;
            case "E":
                return Direction.EAST;
            case "N":
                return Direction.NORTH;
            default:
                return Direction.SOUTH;
        }  // FIXME Task 2.c (P)
    }

    /**
     * Determine whether a given tile is adjacent to this tile instance,
     * and if so, the direction in which it is adjacent.
     *
     * For example: Tile tile1 is in position 0, tile other is in position 1.
     * other is adjacent to tile 1 in the Direction East, so the method would
     * return EAST.
     *
     * @param other the other tile
     * @return The direction of adjacency if the tiles are adjacent, or null if
     * they are not adjacent.
     */
    public Direction adjacencyDirection(Tile other) {
        if (this.position+1==other.position) {
            if (this.position!=2&&this.position!=5&&this.position!=8){
                return Direction.EAST;
            }
        }else if (this.position-1==other.position) {
            if (this.position!=0&&this.position!=3&&this.position!=6){
                return Direction.WEST;
            }
        }else if (this.position+3==other.position) {
            if (this.position!=6&&this.position!=7&&this.position!=8){
                return Direction.SOUTH;
            }
        } else if (this.position-3==other.position) {
            if (this.position!=0&&this.position!=1&&this.position!=2){
                return Direction.NORTH;
            }
        }
        return null;
    }// FIXME Task 7 (CR)

    /**
     * Determine whether a peg can move between this tile and another (other).
     *
     * Conditions for this to be true:
     * - The tiles must be adjacent.
     * - The tiles must have compatible tile types:
     *   - The tiles must both have exits that are on the abutting side.
     *   - The tiles must be at the same level on their abutting sides (both high or both low).
     *
     * NOTE: This test does not consider whether a peg can end its movement on these
     * tiles, only whether it can move between them (i.e. whether this pair of tiles can
     * form part of a path for a peg to transit along).
     *
     * @param other The other tile being considered.
     * @return true if a peg can legally transit from this tile to the other.
     */
    public boolean canTransit(Tile other) {
        Direction otherD = adjacencyDirection(other);
        Direction otherAOppo = other.exitAFaces().getOpposite();
        Direction otherBOppo = other.exitBFaces().getOpposite();
            if (otherD==this.exitAFaces()){
                if (this.getTileType().exitAisGreen()){
                    if (other.getTileType()==TileType.GREEN_CORNER||other.getTileType()==TileType.STRAIGHT){
                        return otherAOppo==this.exitAFaces()||otherBOppo==this.exitAFaces();
                    }else if (other.getTileType()==TileType.STAIRCASE){
                        return otherAOppo==this.exitAFaces();
                    }else return false;
                }else {
                    if (other.getTileType()==TileType.BROWN_CORNER){
                        return otherAOppo==this.exitAFaces()||otherBOppo==this.exitAFaces();
                    }else if (other.getTileType()==TileType.STAIRCASE){
                        return otherBOppo==this.exitAFaces();
                    }else return false;
                }
            }else if (otherD==this.exitBFaces()){
                if (this.getTileType().exitBisGreen()){
                    if (other.getTileType()==TileType.GREEN_CORNER||other.getTileType()==TileType.STRAIGHT){
                        return otherAOppo==this.exitBFaces()||otherBOppo==this.exitBFaces();
                    }else if (other.getTileType()==TileType.STAIRCASE){
                        return otherAOppo==this.exitBFaces();
                    }else return false;
                }else {
                    if (other.getTileType()==TileType.BROWN_CORNER){
                        return otherAOppo==this.exitBFaces()||otherBOppo==this.exitBFaces();
                    }else if (other.getTileType()==TileType.STAIRCASE){
                        return otherBOppo==this.exitBFaces();
                    }else return false;
                }
            }else return false;
    }// FIXME Task 10 (D)

    /** @return the orientation of this tile */
    public Direction getOrientation() { return orientation; }

    /** @return the TileName of this tile */
    public TileName getTileName() { return tileName; }

    /** @return the TileType of this tile */
    public TileType getTileType() { return tileName.getType(); }

    /**
     * Set the position of this tile
     * @param position the new position of this tile.
     */
    public void setPosition(int position) { this.position = position; }

    /** @return the position of this tile */
    public int getPosition() { return position; }

    /** @return true if this tile able to hold the peg */
    public boolean canTakePeg() { return getTileType().canTakePeg(); }

    /**
     * @param viaA if true, we're referring to exiting via A, otherwise via B
     * @return true if the given exit is Green.
     */
    public boolean exitIsGreen(boolean viaA) {
        return viaA ? getTileType().exitAisGreen() : getTileType().exitBisGreen();
    }

    /**
     * @return The direction of exit a faces (in its current orientation).
     */
    public Direction exitAFaces() {
        return getTileType().exitAFaces(orientation);
    }

    /**
     * @return The direction of exit b faces (in its current orientation).
     */
    public Direction exitBFaces() {
        return getTileType().exitBFaces(orientation);
    }

    /**
     * Return a string representation of this tile.
     * @return A string consisting of the tile name's symbol followed by the symbol
     * for the tile's orientation.
     */
    @Override
    public String toString() {
        return tileName+orientation.getSymbol();
    }
}