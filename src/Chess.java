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

    /**
     * Buckle up kiddos, this is gonna be a wild ride
     *
     * Determines if a piece can be moved to the given position.
     * @param piece the piece in question
     * @param xPos the horizontal position in question (represented in pixels)
     * @param yPos the vertical position in question (represented in pixels)
     */
    @Override
    public boolean movePiece(Piece piece, int xPos, int yPos) {
        // Convert the pixel location to a Square
        Square possibleSquare = new Square(420+xPos*80, yPos*80);
        Square currentSquare = piece.getPosition();

        // Keep track of possible moves for the piece
        LinkedList<Square> possibleMoves = new LinkedList<>();

        // Determine what type of piece it is
        switch (piece.getType())  {
            // If it's a pawn, it can only move forward one or two spaces
            case "pawn":
                possibleMoves.add(new Square(currentSquare.row, currentSquare.column++));
                if (currentSquare.row == 1 || currentSquare.row == 7) {
                    possibleMoves.add(new Square(currentSquare.row, currentSquare.column+2));
                }
                break;
            // If it's a rook, it can only move horizontally and vertically
            case "rook":
                for (int i = currentSquare.row; i < 8; i++) {
                    possibleMoves.add(new Square(i, currentSquare.column));
                }
                for (int i = currentSquare.column; i < 8; i++) {
                    possibleMoves.add(new Square(currentSquare.row, i));
                }
                for (int i = currentSquare.row; i >=0; i--) {
                    possibleMoves.add(new Square(i, currentSquare.column));
                }
                for (int i = currentSquare.column; i >= 8; i--) {
                    possibleMoves.add(new Square(currentSquare.row, i));
                }
                break;
            // If it's a knight, it can only move in an L shape, 3x2 Squares
            case "knight":
                // TODO do this one lol

                break;
            // If it's a bishop, it's diagonals only
            case "bishop":
                for (int i = currentSquare.row; i < 8; i++) {
                    possibleMoves.add(new Square(currentSquare.row++, currentSquare.column++));
                    possibleMoves.add(new Square(currentSquare.row++, currentSquare.column--));

                }
                for (int i = currentSquare.row; i > 8; i--) {
                    possibleMoves.add(new Square(currentSquare.row--, currentSquare.column++));
                    possibleMoves.add(new Square(currentSquare.row--, currentSquare.column--));
                }
                break;
            // If it's a king, it can only move one space in any direction
            case "king":
                possibleMoves.add(new Square(currentSquare.row++, currentSquare.column));
                possibleMoves.add(new Square(currentSquare.row--, currentSquare.column));
                possibleMoves.add(new Square(currentSquare.row, currentSquare.column++));
                possibleMoves.add(new Square(currentSquare.row, currentSquare.column--));
                break;
            // If it's a queen, it can move anywhere
            case "queen":
                for (int i = currentSquare.row; i < 8; i++) {
                    possibleMoves.add(new Square(currentSquare.row++, currentSquare.column++));
                    possibleMoves.add(new Square(currentSquare.row++, currentSquare.column--));
                    possibleMoves.add(new Square(currentSquare.row++, currentSquare.column));
                    possibleMoves.add(new Square(currentSquare.row, currentSquare.column++));
                }
                for (int i = currentSquare.row; i >= 0; i--) {
                    possibleMoves.add(new Square(currentSquare.row--, currentSquare.column++));
                    possibleMoves.add(new Square(currentSquare.row--, currentSquare.column--));
                    possibleMoves.add(new Square(currentSquare.row--, currentSquare.column));
                    possibleMoves.add(new Square(currentSquare.row, currentSquare.column++));
                }
                break;

            default:
                break;
        }

        return checkMoves(possibleMoves, xPos, yPos);

    }

    @Override
    public void takePiece(Piece taker, Piece takee) {

    }

    @Override
    public void win(Boolean player) {
    }
}
