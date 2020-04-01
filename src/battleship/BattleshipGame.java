package battleship;

import java.util.Scanner;

/**
 * Class in which user interaction occurs.
 */
public class BattleshipGame {
    /**
     * The main flow of the game.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String endMessage = "";
        int bestResult = 100;
        while (!endMessage.equals("yes")) {
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();
            System.out.println("Game was started!");
            while (!ocean.isGameOver()) {
                int[] coordinates = BattleshipGame.getUserCoordinates();
                System.out.println(ocean.shootAt(coordinates[0], coordinates[1]));
                ocean.print();
            }
            if (bestResult > ocean.getShotsFired()) {
                bestResult = ocean.getShotsFired();
                System.out.println("It's a new record!");
            }
            System.out.println("Game over!\nShots: " + ocean.getShotsFired() + "\nHits: " + ocean.getHitCount()
                    + "\nBest result: " + bestResult + "\nIf you want to exit, type \"yes\". If you want to play again type anything.");
            endMessage = scanner.nextLine();
        }
    }

    /**
     * User enters coordinates here and it parse.
     *
     * @return coordinates of shot
     */
    private static int[] getUserCoordinates() {
        boolean isInputCorrect = false;
        int[] coordinates = new int[2];
        Scanner scanner = new Scanner(System.in);
        while (!isInputCorrect) {
            try {
                System.out.print("Enter a row: ");
                coordinates[0] = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter a column: ");
                coordinates[1] = Integer.parseInt(scanner.nextLine());
                if (coordinates[1] < 0 || coordinates[1] > 9 || coordinates[0] < 0 || coordinates[0] > 9) {
                    System.out.println("Coordinates must be between  0 and 9");
                    continue;
                }
                isInputCorrect = true;
            } catch (Exception ex) {
                System.out.println("Input was incorrect, please, enter coordinates again!");
            }
        }
        return coordinates;
    }
}
