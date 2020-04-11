import java.awt.*;
import java.util.LinkedList;

/**
 * Class representing the board game Chess playable using the Knight's Party Table
 * @author burke
 */
public class Chess extends Game {
    public LinkedList<Piece> pieces;
    public String name = "Chess";
    private int nPieces = 32;
    Board board = new Board("chessboard");

    public Chess () {
        this.pieces = new LinkedList<>();
        fillPieces();
    }

    @Override
    public void start() {

        display();
    }
    private void fillPieces () {
        int row = 0;
        int column = 0;
        int xPos;
        int yPos;
        for (int i = 0; i < nPieces/2; i++) {
            xPos = 420 + (column*80);
            yPos = row * 80;
            // Row 0 is the complicated row: rook, knight, bishop, QUEEN ON COLOR, king, bishop, etc.
            Piece piece = null;

            if (row == 0) {
                if (column == 0) {
                    piece = new Piece(xPos, yPos, true, "rook");
                    piece.setPosition(row, column);
                    column++;
                } else if (column == 1 || column == 6) {
                    piece = new Piece(xPos, yPos, true, "knight");
                    piece.setPosition(row, column);
                    column++;
                } else if (column == 2 || column == 5) {
                    piece = new Piece(xPos, yPos, true, "bishop");
                    piece.setPosition(row, column);
                    column++;
                } else if (column == 3) {
                    piece = new Piece(xPos, yPos, true, "queen");
                    piece.setPosition(row, column);
                    column++;
                } else if (column == 4) {
                    piece = new Piece(xPos, yPos, true, "king");
                    piece.setPosition(row, column);
                    column++;
                } else {
                    piece = new Piece(xPos, yPos, true, "rook");
                    piece.setPosition(row, column);
                    row ++;
                    column = 0;
                }
            } else {
                piece = new Piece(xPos, yPos, true, "pawn");
                piece.setPosition(row, column);
                column++;
            }
            pieces.add(piece);
            System.out.println("Piece added");
        }

        row = 6;
        column = 0;
        for (int i = 16; i < nPieces; i++) {
            xPos = 420 + (column*80);
            yPos = row * 80;
            Piece piece = null;
            if ((row == 6) && (column <= 7)) {
                piece = new Piece(xPos, yPos, false, "pawn");
                piece.setPosition(row, column);
                column++;

            } else if ((row == 6) && (column == 7)) {
                row++;
            } else {
                if (column == 0) {
                    piece = new Piece(xPos, yPos, false, "rook");
                    piece.setPosition(row, column);
                    column++;
                } else if (column == 1 || column == 6) {
                    piece = new Piece(xPos, yPos, false, "knight");
                    piece.setPosition(row, column);
                    column++;
                } else if (column == 2 || column == 5) {
                    piece = new Piece(xPos, yPos, false, "bishop");
                    piece.setPosition(row, column);
                    column++;
                } else if (column == 3) {
                    piece = new Piece(xPos, yPos, false, "queen");
                    piece.setPosition(row, column);
                    System.out.println("We here");
                    column++;
                } else if (column == 4) {
                    piece = new Piece(xPos, yPos, false, "king");
                    piece.setPosition(row, column);
                    column++;
                } else {
                    piece = new Piece(xPos, yPos, false, "rook");
                    piece.setPosition(row, column);
                    row++;
                    column = 0;
                }
            }
            pieces.add(piece);
            System.out.println("Piece added");
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
    public boolean movePiece(Piece piece, int xPos, int yPos) {
        return true;
    }

    @Override
    public void takePiece(Piece taker, Piece takee) {

    }

    @Override
    public void win(Boolean player) {

    }
}
