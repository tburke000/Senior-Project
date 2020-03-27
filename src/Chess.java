import java.awt.*;
import java.util.LinkedList;

public class Chess extends Game {
    public LinkedList<Piece> pieces;
    public String name = "Chess";
    private int nPieces = 32;
    Board board = new Board("chessboard");

    @Override
    public void start() {
        display();
    }

    private void fillCheckers () {
        int row = 0;
        int column = 0;

        for (int i = 0; i < pieces.size() / 2; i++) {
            // Row 0 is the complicated row: rook, knight, bishop, QUEEN ON COLOR, king, bishop, etc.
            Piece piece = new Piece(0, 0, true, null);
            piece.setPosition(row, column);
            if (row == 0) {
                if (column == 0) {
                    piece.setType("rook");
                    column++;
                } else if (column == 1 || column == 6) {
                    piece.setType("knight");
                    column++;
                } else if (column == 2 || column == 5) {
                    piece.setType("bishop");
                    column++;
                } else if (column == 4) {
                    piece.setType("queen");
                    column++;
                } else if (column == 5) {
                    piece.setType("queen");
                    column++;
                } else {
                    piece.setType("rook");
                    row ++;
                    column = 0;
                }

            } else if (row == 1) {
                piece.setType("pawn");
                piece.setPosition(row, column);
                column++;
            } else {
                row++;
            }
        }

        row = 6;
        column = 0;
        for (int i = 16; i < pieces.size(); i++) {
            Piece piece = new Piece(0, 0, false, null);
            piece.setPosition(row, column);
            if (row == 7) {
                if (column == 0) {
                    piece.setType("rook");
                    column++;
                } else if (column == 1 || column == 6) {
                    piece.setType("knight");
                    column++;
                } else if (column == 2 || column == 5) {
                    piece.setType("bishop");
                    column++;
                } else if (column == 4) {
                    piece.setType("king");
                    column++;
                } else if (column == 5) {
                    piece.setType("queen");
                    column++;
                } else {
                    piece.setType("rook");
                    row ++;
                    column = 0;
                }

            } else if (row == 6) {
                piece.setType("pawn");
                piece.setPosition(row, column);
                column++;
            } else {
                row++;
        }
        }

    }

    @Override
    public void display() {
        //Add background,buttons, pieces, and other images to frame
        board.display();
        for (Piece i: pieces) {
            PiecePanel visual = new PiecePanel(i);

            board.gameWindow.add(visual);


        }
    }

    @Override
    public void movePiece(Piece piece) {

    }

    @Override
    public void takePiece(Piece taker, Piece takee) {

    }

    @Override
    public void win(Boolean player) {

    }
}
