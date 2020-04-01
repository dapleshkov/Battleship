package battleship;

import java.io.ObjectOutputStream;

/**
 * The class which represents the battle field and actions with ships
 */
public class Ocean {
    /**
     * the array of ships representing the battle field
     */
    private Ship[][] ships = new Ship[10][10];
    /**
     * the total number of shots
     */
    private int shotsFired;
    /**
     * the number of hits
     */
    private int hitCount;
    /**
     * the number of ships were sunk
     */
    private int shipsSunk;

    /**
     * make all locations as empty sea and give fields default values
     */
    public Ocean() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ships[i][j] = new EmptySea();
            }
        }
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
    }

    /**
     * places all ships randomly
     */
    void placeAllShipsRandomly() {
        placeShipRandomly(1, "Battleship");
        placeShipRandomly(2, "Cruiser");
        placeShipRandomly(3, "Destroyer");
        placeShipRandomly(4, "Submarine");
    }

    /**
     * locates current type of ship random in the field
     *
     * @param n    the number of ships of current type
     * @param type the type of the ship
     */
    private void placeShipRandomly(int n, String type) {
        var position = getRandomPosition();
        boolean orientation;
        Ship ship = new Ship();
        for (int i = 0; i < n; i++) {
            switch (type) {
                case "Battleship": {
                    ship = new Battleship();
                    break;
                }
                case "Cruiser": {
                    ship = new Cruiser();
                    break;
                }
                case "Destroyer": {
                    ship = new Destroyer();
                    break;
                }
                case "Submarine": {
                    ship = new Submarine();
                    break;
                }
            }
            orientation = getRandomOrientation();
            while (isOccupied(position[0], position[1]) || !ship.okToPlaceShipAt(position[0], position[1], orientation, this)) {
                position = getRandomPosition();
                orientation = getRandomOrientation();
            }
            ship.placeShipAt(position[0], position[1], orientation, this);
        }
    }

    /**
     * returns random orientation
     *
     * @return random orientation
     */
    private boolean getRandomOrientation() {
        int orientation = (int) (Math.random() * 2);
        return orientation == 1;
    }

    /**
     * returns random position in the field
     *
     * @return random position in the field
     */
    private int[] getRandomPosition() {
        var position = new int[2];
        position[0] = (int) (Math.random() * 10);
        position[1] = (int) (Math.random() * 10);
        return position;
    }

    /**
     * return if the ship is in this location
     *
     * @param row    the number of row
     * @param column the number of column
     * @return if the ship is in this location
     */
    private boolean isOccupied(int row, int column) {
        return !(getShipArray()[row][column].getClass().getName().equals("battleship.EmptySea"));
    }

    /**
     * returns the result of shot and mark the hit mass if user guessed the location of the ship.
     *
     * @param row    the number of row
     * @param column the number of column
     * @return the result of shot
     */
    String shootAt(int row, int column) {
        shotsFired++;
        if (getShipArray()[row][column].isSunk() || getShipArray()[row][column].getClass().getName().equals("battleship.EmptySea")) {
            if (getShipArray()[row][column].getClass().getName().equals("battleship.EmptySea")) {
                ships[row][column].shootAt(row, column);
                return "Miss.";
            } else {
                return "This ship is sunk already.";
            }
        } else {
            if (ships[row][column].shootAt(row, column)) {
                shipsSunk += 1;
                hitCount++;
                return "You have just sunk " + ships[row][column].getShipType();
            }
            hitCount++;
            return "Hit!";
        }
    }

    /**
     * return the total number of shots.
     *
     * @return the total number of shots.
     */
    int getShotsFired() {
        return shotsFired;
    }

    /**
     * return the number of hits
     *
     * @return the number of hits
     */
    int getHitCount() {
        return hitCount;
    }

    /**
     * return the number of sunk ships
     *
     * @return the number of sunk ships
     */
    private int getShipsSunk() {
        return shipsSunk;
    }

    /**
     * return is game over
     *
     * @return boolean if game over
     */
    boolean isGameOver() {
        return (getShipsSunk() == 10);
    }

    /**
     * Returns private field.
     *
     * @return private field ships
     */
    Ship[][] getShipArray() {
        return ships;
    }

    /**
     * Prints current state if battle field.
     */
    void print() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(getShipArray()[i][j].toString(i, j) + " ");
            }
            System.out.println("");
        }
    }
}
