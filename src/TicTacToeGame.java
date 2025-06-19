import model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    Deque<Player> players;
    Board board;

    public TicTacToeGame() {
        initGame();
    }

    public void initGame() {

        players = new LinkedList<>();
        PlayingPieceX playingPieceX = new PlayingPieceX();
        Player p1 = new Player("P1", playingPieceX);

        PlayingPieceO playingPieceO = new PlayingPieceO();
        Player p2 = new Player("P2", playingPieceO);

        players.add(p1);
        players.add(p2);

        board = new Board(3);
    }

    public String startGame() {

        boolean noWinner = true;
        while(noWinner) {

            // Takeout the player whose turn is
            Player playerTurn = players.removeFirst();

            // print the board
            board.printBoard();

            // get the free spaces in the board
            List<Pair<Integer, Integer>> freeSpaces = board.getFreeCells();
            if(freeSpaces.isEmpty()) { // no free space left
                noWinner = false;
                continue;
            }

            // read the user input
            System.out.println("Player: " + playerTurn.getName() + " Enter row,column: ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String[] values = s.split(",");
            int row = Integer.parseInt(values[0]);
            int col = Integer.parseInt(values[1]);

            // place the piece
            boolean pieceAddedSuccessfully = board.addPiece(row, col, playerTurn.getPlayingPiece());
            if(!pieceAddedSuccessfully) {
                // player cannot add piece here
                System.out.println("Incorrect position, try again");
                players.addFirst(playerTurn);
                continue;
            }

            //put the player in the list back
            players.addLast(playerTurn);

            boolean winner = checkForWinner(row, col, playerTurn.getPlayingPiece().pieceType);
            if(winner) {
                return playerTurn.getName();
            }
        }

        return "Tie";
    }

    public boolean checkForWinner(int row, int col, PieceType pieceType) {

        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        // check in row
        for(int i = 0; i < board.getSize(); i++) {
            if(board.getBoard()[row][i] == null || board.getBoard()[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        // check in col
        for(int i = 0; i < board.getSize(); i++) {
            if(board.getBoard()[i][col] == null || board.getBoard()[i][col].pieceType != pieceType) {
                colMatch = false;
            }
        }

        // check diagonals
        for(int i = 0, j = 0; i < board.getSize(); i++, j++) {
            if(board.getBoard()[i][j] == null || board.getBoard()[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        // check anti-diagonals
        for(int i = 0, j = board.getSize() - 1; i < board.getSize(); i++, j--) {
            if(board.getBoard()[i][j] == null || board.getBoard()[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;
    }
}
