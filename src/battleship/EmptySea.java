package battleship;

/**
 * The class representing the empty location in the field.
 */
public class EmptySea extends Ship {
    /**
     * Shows if user shot on this location.
     */
    private boolean wasShot = false;

    /**
     * Set length location.
     */
    EmptySea() {
        length = 1;
    }

    @Override
    boolean shootAt(int row, int column) {
        wasShot = true;
        return false;
    }

    @Override
    boolean isSunk() {
        return false;
    }

    @Override
    public String toString(int row, int column) {
        if (wasShot) return "-";
        else return ".";
    }
}
