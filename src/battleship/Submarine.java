package battleship;

/**
 * The class representing the ship of type submarine.
 */
public class Submarine extends Ship {
    /**
     * Set length of ship and the length of hit-array.
     */
    public Submarine() {
        length = 1;
        hit = new boolean[length];
    }

    @Override
    String getShipType() {
        return "submarine";
    }

}
