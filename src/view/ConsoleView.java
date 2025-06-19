package view;

import model.Board;
import model.Pair;
import model.Player;
import model.PlayingPiece;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    public void printBoard(Board board) {
        int size = board.getSize();
        PlayingPiece[][] matrix = board.getBoard();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != null) {
                    System.out.print(matrix[i][j].pieceType.name() + "   ");
                } else {
                    System.out.print("    ");
                }
                System.out.print("| ");
            }
            System.out.println();
        }
    }

    public Pair<Integer, Integer> getNextMove(Player player) {
        System.out.println("Player: " + player.getName() + " Enter row,column: ");
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        return new Pair<>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public void printInvalidMove() {
        System.out.println("Invalid move. Try again.");
    }

    public void printWinner(String name) {
        System.out.println("Game Winner: " + name);
    }

    public void printTie() {
        System.out.println("It's a Tie!");
    }
}