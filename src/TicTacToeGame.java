import controller.GameController;
import factory.PlayingPieceFactory;
import model.*;
import strategy.DefaultWinningStrategy;
import strategy.WinningStrategy;
import view.ConsoleView;

import java.util.Deque;
import java.util.LinkedList;

public class TicTacToeGame {
    private TicTacToeGame() {}

    private static class Holder {
        private static final TicTacToeGame instance = new TicTacToeGame();
    }

    public static TicTacToeGame getInstance() {
        return Holder.instance;
    }

    public String startGame() {
        Board board = new Board(3);
        WinningStrategy strategy = new DefaultWinningStrategy();
        ConsoleView view = new ConsoleView();

        Deque<Player> players = new LinkedList<>();
        players.add(new Player("P1", PlayingPieceFactory.createPiece(PieceType.X)));
        players.add(new Player("P2", PlayingPieceFactory.createPiece(PieceType.O)));

        GameController controller = new GameController(board, players, strategy, view);
        return controller.startGame();
    }
}
