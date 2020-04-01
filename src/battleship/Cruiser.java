package battleship;

/**
 * The class representing the ship of type cruiser.
 */
public class Cruiser extends Ship {
    /**
     * Set length of ship and the length of hit-array.
     */
    public Cruiser() {
        length = 3;
        hit = new boolean[length];
    }

    @Override
    String getShipType() {
        return "cruiser";
    }

}
