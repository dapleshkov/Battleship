package battleship;

/**
 * The class representing the ship of type destroyer.
 */
public class Destroyer extends Ship {
    /**
     * Set length of ship and the length of hit-array.
     */
    public Destroyer() {
        length = 2;
        hit = new boolean[length];
    }

    @Override
    String getShipType() {
        return "destroyer";
    }

}
