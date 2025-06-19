package strategy;

import model.Board;
import model.PieceType;
import model.PlayingPiece;

public class DefaultWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, int row, int col, PieceType pieceType) {
        int size = board.getSize();
        PlayingPiece[][] matrix = board.getBoard();

        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        for (int i = 0; i < size; i++) {
            if (matrix[row][i] == null || matrix[row][i].pieceType != pieceType)
                rowMatch = false;
            if (matrix[i][col] == null || matrix[i][col].pieceType != pieceType)
                colMatch = false;
            if (matrix[i][i] == null || matrix[i][i].pieceType != pieceType)
                diagonalMatch = false;
            if (matrix[i][size - 1 - i] == null || matrix[i][size - 1 - i].pieceType != pieceType)
                antiDiagonalMatch = false;
        }

        return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;
    }
}
