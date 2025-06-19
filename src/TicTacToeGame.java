import factory.PlayingPieceFactory;
import model.*;
import strategy.DefaultWinningStrategy;
import strategy.WinningStrategy;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    Board board;
    WinningStrategy winningStrategy;

    private TicTacToeGame() {
        initGame();
    }

    private static class Holder {
        private static final TicTacToeGame instance = new TicTacToeGame();
    }

    public static TicTacToeGame getInstance() {
        return Holder.instance;
    }

    public void initGame() {
        players = new LinkedList<>();
        Player p1 = new Player("P1", PlayingPieceFactory.createPiece(PieceType.X));
        Player p2 = new Player("P2", PlayingPieceFactory.createPiece(PieceType.O));

        players.add(p1);
        players.add(p2);

        board = new Board(3);
        winningStrategy = new DefaultWinningStrategy();
    }

    public String startGame() {
        boolean noWinner = true;
        Scanner sc = new Scanner(System.in);

        while (noWinner) {
            Player currentPlayer = players.removeFirst();
            printBoard();

            List<Pair<Integer, Integer>> freeSpaces = board.getFreeCells();
            if (freeSpaces.isEmpty()) {
                return "Tie";
            }

            System.out.println("Player: " + currentPlayer.getName() + " Enter row,column: ");
            String[] parts = sc.nextLine().split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            boolean added = board.addPiece(row, col, currentPlayer.getPlayingPiece());
            if (!added) {
                System.out.println("Invalid move. Try again.");
                players.addFirst(currentPlayer);
                continue;
            }

            players.addLast(currentPlayer);

            boolean won = winningStrategy.checkWinner(board, row, col, currentPlayer.getPlayingPiece().pieceType);
            if (won) {
                return currentPlayer.getName();
            }
        }

        return "Tie";
    }

    private void printBoard() {
        PlayingPiece[][] matrix = board.getBoard();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
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
}
