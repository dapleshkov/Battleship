package battleship;

public class Ship {
    private int bowRow;
    private int bowColumn;
    public int length;
    boolean horizontal;
    boolean[] hit;

    private int getRowBow() {
        return bowRow;
    }

    int getColumnBow() {
        return bowColumn;
    }

    int getLength() {
        return length;
    }

    void setBowRow(int row) {
        bowRow = row;
    }

    void setBowColumn(int column) {
        bowColumn = column;
    }

    void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * returns the type of ship
     *
     * @return type of ship
     */
    String getShipType() {
        return "";
    }

    /**
     * return if it is ok to place ship in this location
     *
     * @param row        the number of row
     * @param column     the number of column
     * @param horizontal the orientation of the ship
     * @param ocean      the instance of battlefield
     * @return if ok to place
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        Ship[][] ships = ocean.getShipArray();
        if (horizontal == true) {
            if (column + getLength() >= 10) return false;
            for (int i = column - 1; i < column + getLength() + 1; i++) {
                for (int j = row - 1; j < row + 2; j++) {
                    try {
                        if (ships[j][i].getClass().getName().equals("battleship.EmptySea")) continue;
                        else return false;
                    } catch (Exception ex) {
                        continue;
                    }
                }
            }
        } else {
            if (row + getLength() >= 10) return false;
            for (int i = row - 1; i < row + getLength() + 1; i++) {
                for (int j = column - 1; j < column + 2; j++) {
                    try {
                        if (ships[i][j].getClass().getName().equals("battleship.EmptySea")) continue;
                        else return false;
                    } catch (Exception ex) {
                        continue;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Place ship in the battlefield
     *
     * @param row        the number of row
     * @param column     the number of column
     * @param horizontal the orientation of the ship
     * @param ocean      the instance of battlefield
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        Ship[][] ships = ocean.getShipArray();
        setBowRow(row);
        setBowColumn(column);
        setHorizontal(horizontal);
        if (horizontal) {
            for (int i = getColumnBow(); i < column + getLength(); i++) {
                ships[row][i] = this;
            }
        } else {
            for (int i = getRowBow(); i < row + getLength(); i++) {
                ships[i][column] = this;
            }
        }
    }

    /**
     * marks the hit array
     *
     * @param row    the number of row
     * @param column the number of column
     * @return if ship sunk
     */
    boolean shootAt(int row, int column) {
        if (horizontal) {
            int hitPart = column - getColumnBow();
            hit[hitPart] = true;
        } else {
            int hitPart = row - getRowBow();
            hit[hitPart] = true;
        }
        return isSunk();
    }

    /**
     * shows if ship sunk
     *
     * @return boolean if ship sunk
     */
    boolean isSunk() {
        for (int i = 0; i < getLength(); i++) {
            if (!hit[i]) return false;
        }
        return true;
    }

    /**
     * returns the symbol which should be typed in the field
     *
     * @param row    the number of row
     * @param column the number of column
     * @return the symbol which should be typed in the field
     */
    public String toString(int row, int column) {
        if (isSunk()) return "x";
        else if (hit[row + column - getRowBow() - getColumnBow()]) return "S";
        else return ".";
    }
}
