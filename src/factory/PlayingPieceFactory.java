package factory;

import model.*;

public class PlayingPieceFactory {
    public static PlayingPiece createPiece(PieceType pieceType) {
        return switch (pieceType) {
            case X -> new PlayingPieceX();
            case O -> new PlayingPieceO();
        };
    }
}