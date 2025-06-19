package strategy;

import model.Board;
import model.PieceType;

public interface WinningStrategy {
    boolean checkWinner(Board board, int row, int col, PieceType pieceType);
}