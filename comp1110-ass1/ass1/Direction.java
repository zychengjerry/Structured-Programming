package comp1110.ass1;

/**
 * This enumeration type represents the four cardinal directions
 *
 * Notice that this is an enumeration type, so none of the fields
 * change once the type is created (they are all declared final).
 *
 * This class contains three tasks that you need to complete.
 */
public enum Direction {
    NORTH('↑'), EAST('→'), SOUTH('↓'), WEST('←');

    final private char symbol;

    /**
     * Constructor
     *
     * @param symbol This direction's symbol
     */
    Direction(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Given an upper case character ('N', 'E', 'S', 'W'),
     * return the Direction associated with this character.
     *
     * @param direction a char value representing the `Direction` enum
     * @return the `Direction` associated with the char.
     */
    public static Direction fromChar(char direction) {// FIXME Task 2.a (P)
        switch (String.valueOf(direction)) {
            case "W":
                return WEST;
            case "E":
                return EAST;
            case "N":
                return NORTH;
            default:
                return SOUTH;
        }
    }

    /**
     * Return the single character associated with a `Direction`, which is
     * the first character of the direction name, as an upper case character
     * ('N', 'E', 'S', 'W')
     *
     * @return The first character of the name of the direction
     */
    public char toChar() {
        switch (this){
            case WEST:
                String w = "W";
                return w.charAt(0);
            case EAST:
                String e = "E";
                return e.charAt(0);
            case NORTH:
                String n = "N";
                return n.charAt(0);
            default:
                String s = "S";
                return s.charAt(0);
        }
        // FIXME Task 2.b (P)
    }

    /**
     * Given a Direction instance, return the opposite Direction
     * @return the opposite Direction to the instance.
     * for example: the opposite Direction of 'NORTH' would be 'SOUTH'.
     */
    public Direction getOpposite() {
        switch (this){
            case WEST:
                return EAST;
            case EAST:
                return WEST;
            case NORTH:
                return SOUTH;
            default:
                return NORTH;
        } // FIXME Task 3 (P)
    }

    /** @return this direction's symbol as a string */
    public String getSymbol() {
        return Character.toString(symbol);
    }

    /**
     * Return a string representation of the direction
     * @return A string consisting of the character returned by the toChar() method
     */
    @Override
    public String toString() {
        return Character.toString(toChar());
    }
}
