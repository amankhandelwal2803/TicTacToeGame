package controller;

import model.*;
import strategy.WinningStrategy;
import view.ConsoleView;

import java.util.Deque;
import java.util.List;

public class GameController {
    private final Board board;
    private final Deque<Player> players;
    private final WinningStrategy winningStrategy;
    private final ConsoleView view;

    public GameController(Board board, Deque<Player> players, WinningStrategy winningStrategy, ConsoleView view) {
        this.board = board;
        this.players = players;
        this.winningStrategy = winningStrategy;
        this.view = view;
    }

    public String startGame() {
        while (true) {
            Player player = players.removeFirst();
            view.printBoard(board);

            List<Pair<Integer, Integer>> freeCells = board.getFreeCells();
            if (freeCells.isEmpty()) {
                view.printTie();
                return "Tie";
            }

            Pair<Integer, Integer> move = view.getNextMove(player);
            int row = move.key;
            int col = move.value;

            boolean added = board.addPiece(row, col, player.getPlayingPiece());
            if (!added) {
                view.printInvalidMove();
                players.addFirst(player);
                continue;
            }

            players.addLast(player);

            boolean won = winningStrategy.checkWinner(board, row, col, player.getPlayingPiece().pieceType);
            if (won) {
                view.printWinner(player.getName());
                return player.getName();
            }
        }
    }
}