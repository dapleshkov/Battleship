package battleship;

/**
 * The class representing the ship of type battleship.
 */
public class Battleship extends Ship {
    /**
     * Set length of ship and the length of hit-array.
     */
    public Battleship() {
        length = 4;
        hit = new boolean[length];
    }

    @Override
    String getShipType() {
        return "battleship";
    }

}
